package com.unibooth.unibooth.domain.booth.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class CommentDto {
    private String content;
}
