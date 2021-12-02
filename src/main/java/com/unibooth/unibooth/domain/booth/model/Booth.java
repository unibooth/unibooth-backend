package com.unibooth.unibooth.domain.booth.model;


import com.unibooth.unibooth.domain.booth.dto.request.BoothCreateDto;
import com.unibooth.unibooth.domain.university.model.University;
import com.unibooth.unibooth.domain.user.model.Entertainer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Booth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private Entertainer entertainer;

    private String university;
    private String location;
    private String type;
    private String date;
    private String stampCode;

    public Booth(Entertainer entertainer, String university, String location, String type, String date) {
        this.entertainer = entertainer;
        this.university = university;
        this.location = location;
        this.type = type;
        this.date = date;
    }

    public static Booth of(
            Entertainer entertainer,
            String university,
            String location,
            String type,
            String date
        ){
        return new Booth(
          entertainer, university, location, type, date
        );
    }
}
