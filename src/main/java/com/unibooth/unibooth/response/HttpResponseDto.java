package com.unibooth.unibooth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class HttpResponseDto {
    public HttpResponseDto(StatusEnum statusEnum, String message) {
        this.statusEnum = statusEnum;
        this.message = message;
    }

    private StatusEnum statusEnum;
    private String message;
    private Object data;
}
