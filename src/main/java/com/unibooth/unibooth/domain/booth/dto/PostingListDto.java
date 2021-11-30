package com.unibooth.unibooth.domain.booth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class PostingListDto {

    List<PostingDto> postingDtoList;
}
