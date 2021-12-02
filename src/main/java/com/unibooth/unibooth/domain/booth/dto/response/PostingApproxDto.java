package com.unibooth.unibooth.domain.booth.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class PostingApproxDto {
    private Long id;
    private String type;
    private String name;
    private byte[] image;
    private int likeCount;
    private List<CommentResDto> comments;

    //아직 못넣음
    private int shareCount;

// 댓글만 있으면 끝
    public PostingApproxDto(Long id, String postingTitle, byte[] coverPhoto, int like, String type, List<CommentResDto> comments) {
        this.id = id;
        this.name = postingTitle;
        this.image = coverPhoto;
        this.likeCount = like;
        this.type = type;
        this.comments =comments;
    }

    public static PostingApproxDto from(Long id, String postingTitle, byte[] coverPhoto, int like, String type, List<CommentResDto> comments) {
        return new PostingApproxDto(id, postingTitle, coverPhoto, like, type, comments);
    }
}
