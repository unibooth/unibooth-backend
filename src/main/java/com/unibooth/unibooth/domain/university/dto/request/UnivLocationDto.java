package com.unibooth.unibooth.domain.university.dto.request;


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
}
