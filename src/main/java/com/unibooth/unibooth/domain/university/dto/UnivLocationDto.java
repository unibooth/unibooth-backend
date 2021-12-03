package com.unibooth.unibooth.domain.university.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Getter
public class UnivLocationDto {

    private double latitude;
    private double longitude;
    private String university;

    public UnivLocationDto(double latitude, double longitude, String university) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.university = university;
    }


    public static UnivLocationDto from(double latitude, double longitude, String university) {
        return new UnivLocationDto(latitude, longitude, university);
    }
}
