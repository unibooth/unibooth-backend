package com.unibooth.unibooth.domain.booth.dto.request;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Getter
public class BoothCreateDto {

    private String type;
    private double latitude;
    private double longitude;
    private String university;
    private String location;
    private String date;
}
