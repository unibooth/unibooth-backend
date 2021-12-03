package com.unibooth.unibooth.domain.university.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class UnivLocation {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    @Column(unique = true)
    private String university;

    public UnivLocation(double latitude, double longitude, String university) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.university = university;
    }

    public static UnivLocation of(double latitude, double longitude, String university) {
        return new UnivLocation(latitude, longitude, university);
    }
}
