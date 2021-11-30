package com.unibooth.unibooth.domain.booth.model;


import com.unibooth.unibooth.domain.university.model.University;
import com.unibooth.unibooth.domain.user.model.Entertainer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Booth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boothCategory;

    @OneToOne(fetch = FetchType.EAGER)
    private Entertainer entertainer;



    @ManyToOne(fetch = FetchType.EAGER)
    private University university;


}
