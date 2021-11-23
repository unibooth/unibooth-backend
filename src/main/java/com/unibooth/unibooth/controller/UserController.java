package com.unibooth.unibooth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibooth.unibooth.config.JwtTokenProvider;
import com.unibooth.unibooth.domain.user.dto.KakaoAPI.KakaoProfileDto;
import com.unibooth.unibooth.domain.user.dto.KakaoAPI.KakaoTokenDto;
import com.unibooth.unibooth.domain.user.dto.UserCreateDto;
import com.unibooth.unibooth.domain.user.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailService userDetailService;

    // this is for frontend logic => just for testing in backend
    @GetMapping("/callback")
    public String kakaoCallBack(@RequestParam("code") String code) {
        System.out.println("code = " + code);
        return code;
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public String kakaoJoin(@RequestParam("token") String authorizeNumber) throws JsonProcessingException {
        return userDetailService.oauthGateway(getUserDtoFromKakaoOauth(authorizeNumber), jwtTokenProvider);
    }

    public String getAccessToken(String authorizeNumber) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = getHttpHeaders();

        MultiValueMap<String, String> params = getAccessTokenRequestParameter(authorizeNumber);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );
        ObjectMapper objectMapper = new ObjectMapper();
        KakaoTokenDto kakaoTokenDto = objectMapper.readValue(response.getBody(), KakaoTokenDto.class);

        return kakaoTokenDto.getAccess_token();
    }

    public MultiValueMap<String, String> getAccessTokenRequestParameter(String authorizeNumber) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "874ba5b114a16c418289f84f50d2fcb2");
        params.add("redirect_uri", "http://localhost:8080/api/user/callback");
        params.add("code", authorizeNumber);

        return params;
    }
    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return httpHeaders;
    }

    public UserCreateDto getUserDtoFromKakaoOauth(String authorizeNumber) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = getAccessToken(authorizeNumber);

        HttpHeaders httpHeaders = getHttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfileDto kakaoProfileDto = objectMapper.readValue(response.getBody(), KakaoProfileDto.class);
        UserCreateDto userCreateDto = UserCreateDto.of(kakaoProfileDto);

        return userCreateDto;
    }
}
