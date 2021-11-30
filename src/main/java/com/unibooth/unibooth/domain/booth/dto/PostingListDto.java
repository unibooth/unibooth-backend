package com.unibooth.unibooth.domain.booth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class PostingListDto {

    private String postingTitle;
    private MultipartFile coverPhoto;
    private List<PostingDto> postingDtoList;
}
