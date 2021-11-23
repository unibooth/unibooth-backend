package com.unibooth.unibooth.domain.user.dto.KakaoAPI;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class Profile {
    private String nickname;
    private String thumbnail_image_url;
    private String profile_image_url;
    private Boolean is_default_image;


}
