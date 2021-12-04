package com.unibooth.unibooth.domain.booth.model;

import com.unibooth.unibooth.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    private Posting posting;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private Date createdAt;



    public Comment(String content, User user, Posting posting) {

        this.content = content;
        this.user = user;
        this.posting = posting;
        this.createdAt = new Date(System.currentTimeMillis());
    }

    public static  Comment of(String content, User user, Posting posting, FileStream image) {
        return new Comment(content, user, posting);
    }
}
