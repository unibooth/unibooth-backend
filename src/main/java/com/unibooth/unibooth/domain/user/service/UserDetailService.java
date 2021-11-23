package com.unibooth.unibooth.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibooth.unibooth.config.JwtTokenProvider;
import com.unibooth.unibooth.domain.user.dto.KakaoAPI.KakaoProfileDto;
import com.unibooth.unibooth.domain.user.dto.KakaoAPI.KakaoTokenDto;
import com.unibooth.unibooth.domain.user.dto.OauthUserCreateDto;
import com.unibooth.unibooth.domain.user.dto.UserCreateDto;
import com.unibooth.unibooth.domain.user.model.User;
import com.unibooth.unibooth.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final Environment environment;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmailElseThrow(username);
    }

    public String userJoin(UserCreateDto userCreateDto, JwtTokenProvider jwtTokenProvider) {
        User user = User.of(userCreateDto);
        userRepository.save(user);
        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }

    public String oauthGateway(UserCreateDto userCreateDto, JwtTokenProvider jwtTokenProvider) {

        Optional<User> user = userRepository.findByEmail(userCreateDto.getEmail());

        if(!user.isPresent()) {
           return userJoin(userCreateDto, jwtTokenProvider);
        }else {
            System.out.println("user.get().getUsername() = " + user.get().getUsername());
            return jwtTokenProvider.createToken(user.get().getUsername(), user.get().getRoles());
        }
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
        String clientId = environment.getProperty("kakao-client-id");
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", "http://localhost:8080/api/user/callback");
        params.add("code", authorizeNumber);

        return params;
    }

    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return httpHeaders;
    }

    public UserCreateDto getUserDtoFromKakaoOauth(OauthUserCreateDto oauthUserCreateDto) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = getAccessToken(oauthUserCreateDto.getAuthorizeToken());

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
        UserCreateDto userCreateDto = UserCreateDto.of(kakaoProfileDto, oauthUserCreateDto.getUniversity());

        return userCreateDto;
    }
}
