package com.unibooth.unibooth.domain.user.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class OauthUserCreateDto {

    private String university;
    private String authorizeToken;
}
