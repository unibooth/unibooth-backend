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

    private String nickname;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FileStream profilePhoto;


    @Column(length = 1000)
    private String introduce;

    public Entertainer(String nickname, String introduce, FileStream profilePhoto) {
        this.nickname = nickname;
        this.introduce = introduce;
        this.profilePhoto = profilePhoto;
    }

    public static Entertainer of(String nickname, String introduce, FileStream profilePhoto) {
        return new Entertainer(nickname,introduce,  profilePhoto);
    }

}
