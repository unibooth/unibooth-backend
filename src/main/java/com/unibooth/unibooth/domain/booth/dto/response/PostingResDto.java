package com.unibooth.unibooth.domain.booth.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class PostingResDto {

    private String postingTitle;
    private byte[] coverPhoto;
    private List<ContentResDto> contentDtoList;

    public PostingResDto(String postingTitle, byte[] coverPhoto, List<ContentResDto> contentDtoList) {
        this.postingTitle = postingTitle;
        this.coverPhoto = coverPhoto;
        this.contentDtoList = contentDtoList;
    }

    public static PostingResDto from(
            String postingTitle,
            byte[] coverPhoto,
            List<ContentResDto> contentDtoList
    ) {

        return new PostingResDto(postingTitle, coverPhoto, contentDtoList);
    }
}
