package com.unibooth.unibooth.domain.booth.dto.response;

import com.unibooth.unibooth.domain.user.dto.response.BoothEntertainerDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class PostingResDto {
    private Long id;
    private BoothEntertainerDto boothEntertainerDto;
    private String postingTitle;
    private byte[] coverPhoto;
    private List<ContentResDto> contentDtoList;
    private int like;



    public PostingResDto(Long id, BoothEntertainerDto boothEntertainerDto, String postingTitle, byte[] coverPhoto, List<ContentResDto> contentDtoList, int like) {
        this.id = id;
        this.boothEntertainerDto = boothEntertainerDto;
        this.postingTitle = postingTitle;
        this.coverPhoto = coverPhoto;
        this.contentDtoList = contentDtoList;
        this.like = like;
    }

    public static PostingResDto from(
            Long id,
            BoothEntertainerDto boothEntertainerDto,
            String postingTitle,
            byte[] coverPhoto,
            List<ContentResDto> contentDtoList,
            int like
    ) {
        return new PostingResDto(id, boothEntertainerDto, postingTitle, coverPhoto, contentDtoList, like);
    }


}
