package com.unibooth.unibooth.domain.booth.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class PostingApproxDto {
    private Long id;
    private String postingTitle;
    private byte[] coverPhoto;
    private int like;

    public PostingApproxDto(Long id, String postingTitle, byte[] coverPhoto, int like) {
        this.id = id;
        this.postingTitle = postingTitle;
        this.coverPhoto = coverPhoto;
        this.like = like;
    }

    public static PostingApproxDto from(Long id, String postingTitle, byte[] coverPhoto, int like) {
        return new PostingApproxDto(id, postingTitle, coverPhoto, like);
    }
}
