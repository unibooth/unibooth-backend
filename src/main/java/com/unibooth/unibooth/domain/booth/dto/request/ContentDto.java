package com.unibooth.unibooth.domain.booth.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class ContentDto {
    private MultipartFile photo;
    private String contents = "";
    private String contentTitle = "";

    // for tag
    private List<TagDto> tagDtoList = new ArrayList<>();
}
