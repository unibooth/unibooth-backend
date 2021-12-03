package com.unibooth.unibooth.domain.booth.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class StampResDto {
    private int count;

    public static StampResDto from(int count) {
        return new StampResDto(count);
    }
}
