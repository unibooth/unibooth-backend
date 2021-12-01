package com.unibooth.unibooth.domain.booth.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class PostingApproxDto {
    private Long id;
    private String type;
    private String name;
    private byte[] image;
    private int likeCount;

    //아직 못넣음
    private int shareCount;

// 댓글만 있으면 끝
    public PostingApproxDto(Long id, String postingTitle, byte[] coverPhoto, int like, String type) {
        this.id = id;
        this.name = postingTitle;
        this.image = coverPhoto;
        this.likeCount = like;
        this.type = type;
    }

    public static PostingApproxDto from(Long id, String postingTitle, byte[] coverPhoto, int like, String type) {
        return new PostingApproxDto(id, postingTitle, coverPhoto, like, type);
    }
}
