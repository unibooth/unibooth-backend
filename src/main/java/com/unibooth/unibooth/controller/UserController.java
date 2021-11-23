package com.unibooth.unibooth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unibooth.unibooth.config.JwtTokenProvider;
import com.unibooth.unibooth.domain.user.dto.OauthUserCreateDto;
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


}
