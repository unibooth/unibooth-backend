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
    private List<CommentResDto> comments;
    private int likeCount;
    private int shareCount;

    private String university;


    public PostingResDto(Long id,
                         String type,
                         EntertainerDto entertainerDto,
                         String postingTitle,
                         byte[] coverPhoto,
                         String location,
                         String date,
                         List<ContentResDto> contentDtoList,
                         int like,
                         List<CommentResDto> comments,
                         String university
    ) {
        this.id = id;
        this.type = type;
        this.entertainer = entertainerDto;
        this.name = postingTitle;
        this.image = coverPhoto;
        this.location = location;
        this.date = date;
        this.contents = contentDtoList;
        this.likeCount = like;
        this.comments = comments;
        this.university = university;
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
            int like,
            List<CommentResDto> comments,
            String university
    ) {
        return new PostingResDto(id,type, boothEntertainerDto, postingTitle, coverPhoto,location, date, contentDtoList, like, comments, university);
    }


}
