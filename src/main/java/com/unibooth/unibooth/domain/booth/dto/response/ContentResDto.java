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
    private String contents;
    private String contentTitle;
    private PhotoFileDto photoFile;
    // for tag

    public ContentResDto(Long id, PhotoFileDto photoFile, String contents, String contentTitle) {
        this.id = id;
        this.photoFile = photoFile;
        this.contents = contents;
        this.contentTitle = contentTitle;
    }

    public static ContentResDto from(
            Long id,
            PhotoFileDto photoFile,
            String contents,
            String contentTitle
    ) {
        return new ContentResDto(id, photoFile, contents, contentTitle);
    }
}
