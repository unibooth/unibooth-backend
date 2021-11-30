package com.unibooth.unibooth.domain.booth.model;

import com.unibooth.unibooth.domain.user.model.Entertainer;
import com.unibooth.unibooth.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<Content> contentsList;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FileStream coverPhoto;
    private String postingTitle;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="heart",
            joinColumns = @JoinColumn(name="posting_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private Set<User> likeUsers = new HashSet<>();


    public Posting(String postingTitle, FileStream coverPhoto, List<Content> contentsList) {
        this.postingTitle = postingTitle;
        this.coverPhoto = coverPhoto;
        this.contentsList = contentsList;
    }

    public static Posting of(
            String postingTitle,
            FileStream coverPhoto,
            List<Content> contentsList
    ) {
        return new Posting(postingTitle, coverPhoto, contentsList);
    }

}
