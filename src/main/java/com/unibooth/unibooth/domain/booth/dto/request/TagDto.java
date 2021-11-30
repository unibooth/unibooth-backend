package com.unibooth.unibooth.domain.booth.dto.request;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class TagDto {
    private String item;
    private int price = 0;
}
