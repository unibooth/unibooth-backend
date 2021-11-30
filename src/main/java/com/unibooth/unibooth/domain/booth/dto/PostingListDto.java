package com.unibooth.unibooth.domain.booth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
// model attribute로 dto 변환시 모든 내용이 post될 필요는 없음.
public class PostingListDto {

    private String postingTitle;
    private MultipartFile coverPhoto;
    private List<ContentDto> contentDtoList;
}
