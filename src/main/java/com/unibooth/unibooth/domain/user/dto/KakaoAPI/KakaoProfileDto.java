package com.unibooth.unibooth.domain.user.dto.KakaoAPI;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Data
@Getter
@NoArgsConstructor
public class KakaoProfileDto {

    private Integer id;
    private String connected_at;
    private Properties properties;
    private KakaoAccount kakao_account;

}


