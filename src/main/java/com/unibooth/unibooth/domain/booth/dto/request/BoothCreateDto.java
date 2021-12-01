package com.unibooth.unibooth.domain.booth.dto.request;

import com.unibooth.unibooth.domain.university.model.University;
import com.unibooth.unibooth.domain.user.model.Entertainer;

import javax.persistence.*;
import java.util.Date;

public class BoothCreateDto {

    private String boothCategory;

    @OneToOne(fetch = FetchType.EAGER)
    private Entertainer entertainer;

    @ManyToOne(fetch = FetchType.EAGER)
    private University university;

    private int locationNum;
    private Date date;
}
