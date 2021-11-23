package com.unibooth.unibooth.domain.user.dto;

import com.unibooth.unibooth.domain.user.dto.KakaoAPI.KakaoProfileDto;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserCreateDto {

    public UserCreateDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public static UserCreateDto of(KakaoProfileDto kakaoProfileDto) {

        return new UserCreateDto(kakaoProfileDto.getKakao_account().getEmail(), kakaoProfileDto.getKakao_account().getProfile().getNickname());
    }

    private String email;
    private String name;

}
