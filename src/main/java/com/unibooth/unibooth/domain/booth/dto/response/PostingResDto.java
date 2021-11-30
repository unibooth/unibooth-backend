package com.unibooth.unibooth.domain.booth.dto.response;

import com.unibooth.unibooth.domain.booth.dto.request.ContentDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Getter
@NoArgsConstructor

public class PostingResDto {

    private String postingTitle;
    private byte[] coverPhoto;
    private List<ContentResDto> contentDtoList;
}
