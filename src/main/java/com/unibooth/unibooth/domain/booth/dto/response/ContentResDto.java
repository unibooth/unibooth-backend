package com.unibooth.unibooth.domain.booth.dto.response;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Getter
@NoArgsConstructor
public class ContentResDto {

    private Long id;
    private String description;
    private String title;
    private byte[] image;

    private List<TagResDto> tags;

    // for tag

    public ContentResDto(Long id, byte[] image, List<TagResDto> tags, String contents, String contentTitle) {
        this.id = id;
        this.image = image;
        this.tags = tags;
        this.description = contents;
        this.title = contentTitle;
    }

    public static ContentResDto from(
            Long id,
            byte[] image,
            List<TagResDto> tags,
            String contents,
            String contentTitle
    ) {
        return new ContentResDto(id, image, tags, contents, contentTitle);
    }
}
