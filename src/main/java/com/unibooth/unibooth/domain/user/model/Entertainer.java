package com.unibooth.unibooth.domain.user.model;


import com.unibooth.unibooth.domain.booth.model.Booth;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Entertainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Booth booth;

    private String personalSNSUrl;

    @Column(length = 1000)
    private String introduce;

}
