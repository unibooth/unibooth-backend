package com.unibooth.unibooth.domain.user.dto.KakaoAPI;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class KakaoAccount {

    private Boolean profile_nickname_needs_agreement;
    private Boolean profile_image_needs_agreement;
    private Profile profile;
    private Boolean has_email;
    private Boolean email_needs_agreement;
    private Boolean is_email_valid;
    private Boolean is_email_verified;
    private String email;
    private Boolean has_age_range;
    private Boolean age_range_needs_agreement;
    private String age_range;
    private Boolean has_birthday;
    private Boolean birthday_needs_agreement;
    private String birthday;
    private String birthday_type;


}