package com.unibooth.unibooth.domain.booth.dto.response;

import com.unibooth.unibooth.domain.user.dto.response.EntertainerDto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class PostingResDto {


    private Long id;
    private String type;
    private EntertainerDto entertainer;
    private String name;
    private byte[] image;

    private String location;
    private String date;
    private List<ContentResDto> contents;

    private int likeCount;
    private int shareCount;


    public PostingResDto(Long id,
                         String type,
                         EntertainerDto entertainerDto,
                         String postingTitle,
                         byte[] coverPhoto,
                         String location,
                         String date,
                         List<ContentResDto> contentDtoList,
                         int like) {
        this.id = id;
        this.type = type;
        this.entertainer = entertainerDto;
        this.name = postingTitle;
        this.image = coverPhoto;
        this.location = location;
        this.date = date;
        this.contents = contentDtoList;
        this.likeCount = like;
    }

    public static PostingResDto from(
            Long id,
            String type,
            EntertainerDto boothEntertainerDto,
            String postingTitle,
            byte[] coverPhoto,
            String location,
            String date,
            List<ContentResDto> contentDtoList,
            int like
    ) {
        return new PostingResDto(id,type, boothEntertainerDto, postingTitle, coverPhoto,location, date, contentDtoList, like);
    }


}
