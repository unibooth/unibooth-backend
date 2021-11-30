package com.unibooth.unibooth.domain.booth.dto.response;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class TagResDto {
    private String item;
    private int price;
}
