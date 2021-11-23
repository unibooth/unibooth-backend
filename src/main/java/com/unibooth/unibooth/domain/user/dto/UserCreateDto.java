package com.unibooth.unibooth.domain.user.dto;

import com.unibooth.unibooth.domain.user.dto.KakaoAPI.KakaoProfileDto;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserCreateDto {

    public UserCreateDto(String email, String name, String university) {
        this.email = email;
        this.name = name;
        this.university = university;
    }

    public static UserCreateDto of(KakaoProfileDto kakaoProfileDto, String university) {

        return new UserCreateDto(kakaoProfileDto.getKakao_account().getEmail(), kakaoProfileDto.getKakao_account().getProfile().getNickname(), university);
    }

    private String email;
    private String name;
    private String university;

}
