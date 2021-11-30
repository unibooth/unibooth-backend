package com.unibooth.unibooth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unibooth.unibooth.config.JwtTokenProvider;
import com.unibooth.unibooth.domain.user.dto.OauthUserCreateDto;
import com.unibooth.unibooth.domain.user.dto.UserCreateDto;
import com.unibooth.unibooth.domain.user.model.User;
import com.unibooth.unibooth.domain.user.repository.UserRepository;
import com.unibooth.unibooth.domain.user.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PropertySource("classpath:application-oauth.properties")
@RequestMapping("/api/user")
public class UserController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailService userDetailService;
    private final UserRepository userRepository;

    @GetMapping("/callback")
    public String kakaoCallBack(@RequestParam("code") String code) {
        System.out.println("code = " + code);
        return code;
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public String kakaoJoin(@RequestBody OauthUserCreateDto oauthUserCreateDto) throws JsonProcessingException {
        return userDetailService.oauthGateway(userDetailService.getUserDtoFromKakaoOauth(oauthUserCreateDto), jwtTokenProvider);
    }

    @GetMapping("/force-token")
    @ResponseStatus(HttpStatus.OK)
    public String getTokenForcely(@RequestParam String userEmail) {
        User user = userRepository.findByEmailElseThrow(userEmail);
        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }

    @GetMapping("/force-join")
    @ResponseStatus(HttpStatus.OK)
    public String joinForcely(@RequestParam String userEmail, @RequestParam String univ, @RequestParam String name) {
        UserCreateDto userCreateDto = new UserCreateDto(userEmail, name, univ);
        userDetailService.userJoin(userCreateDto, jwtTokenProvider);
        return "success";
    }


}
