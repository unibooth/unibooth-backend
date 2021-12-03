package com.unibooth.unibooth.response.exception;

import com.unibooth.unibooth.response.HttpResponseDto;
import com.unibooth.unibooth.response.StatusEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.charset.Charset;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<HttpResponseDto> handler(NullPointerException e) {
        HttpResponseDto httpResponseDto =
                new HttpResponseDto(StatusEnum.PARAMETER_LACKED, e.getMessage());
        return new ResponseEntity<>(httpResponseDto, getHttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<HttpResponseDto> handler(SQLIntegrityConstraintViolationException e) {
        HttpResponseDto httpResponseDto =
                new HttpResponseDto(StatusEnum.BAD_REQUEST, e.getMessage());
        return new ResponseEntity<>(httpResponseDto, getHttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return headers;
    }
}
