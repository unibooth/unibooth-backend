package com.unibooth.unibooth.domain.booth.model;

import com.unibooth.unibooth.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
}
