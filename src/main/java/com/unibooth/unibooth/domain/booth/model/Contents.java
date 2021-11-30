package com.unibooth.unibooth.domain.booth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@NoArgsConstructor
@Getter
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postingTitle;

    @Column(length = 3000)
    private String explain;
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FileStream fileStream;

}
