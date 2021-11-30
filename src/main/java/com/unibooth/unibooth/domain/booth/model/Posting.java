package com.unibooth.unibooth.domain.booth.model;

import com.unibooth.unibooth.domain.user.model.Entertainer;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Booth booth;

    @ManyToOne(fetch = FetchType.EAGER)
    private Entertainer entertainer;

    private String category;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contents> contentsList;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FileStream coverPhoto;
    private String postingTitle;

    public Posting(String postingTitle, FileStream coverPhoto, List<Contents> contentsList) {
        this.postingTitle = postingTitle;
        this.coverPhoto = coverPhoto;
        this.contentsList = contentsList;
    }

    public static Posting of(
            String postingTitle,
            FileStream coverPhoto,
            List<Contents> contentsList
    ) {
        return new Posting(postingTitle, coverPhoto, contentsList);
    }

}
