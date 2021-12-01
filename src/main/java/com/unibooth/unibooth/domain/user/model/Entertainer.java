package com.unibooth.unibooth.domain.user.model;


import com.unibooth.unibooth.domain.booth.model.FileStream;
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

    private String name;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FileStream profilePhoto;

    private String personalSNSUrl;

    @Column(length = 1000)
    private String introduce;

    public Entertainer(String name, String introduce, FileStream profilePhoto) {
        this.name = name;
        this.introduce = introduce;
        this.profilePhoto = profilePhoto;
    }

    public static Entertainer of(String name, String introduce, FileStream profilePhoto) {
        return new Entertainer(name,introduce,  profilePhoto);
    }

}
