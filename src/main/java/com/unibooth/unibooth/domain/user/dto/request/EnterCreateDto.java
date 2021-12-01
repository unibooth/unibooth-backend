package com.unibooth.unibooth.domain.user.dto.request;

import com.unibooth.unibooth.domain.booth.model.Booth;
import com.unibooth.unibooth.domain.booth.model.FileStream;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
public class EnterCreateDto {
    private String nickname;
    private MultipartFile profilePhoto;
    private String introduce;
}
