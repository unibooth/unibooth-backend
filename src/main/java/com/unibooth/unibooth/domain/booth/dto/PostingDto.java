package com.unibooth.unibooth.domain.booth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@Getter
public class PostingDto {
    private MultipartFile photo;
    private String explain;
    private String tag;
}
