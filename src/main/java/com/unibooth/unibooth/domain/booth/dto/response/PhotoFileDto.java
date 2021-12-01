package com.unibooth.unibooth.domain.booth.dto.response;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class PhotoFileDto {
    private Long id;
    private byte[] photo;
    private List<TagResDto> tagResDto;

    public PhotoFileDto(byte[] photo, List<TagResDto> tagResDto) {
        this.photo = photo;
        this.tagResDto = tagResDto;
    }

    public static PhotoFileDto from(
            byte[] photo,
            List<TagResDto> tagResDto
    ) {
        return new PhotoFileDto(photo, tagResDto);
    }
}
