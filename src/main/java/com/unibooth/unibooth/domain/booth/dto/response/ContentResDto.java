package com.unibooth.unibooth.domain.booth.dto.response;


import com.unibooth.unibooth.domain.booth.dto.request.TagDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class ContentResDto {

    private byte[] photo;
    private String contents;
    private String contentTitle;

    // for tag
    private List<TagResDto> tagDtoList;
}
