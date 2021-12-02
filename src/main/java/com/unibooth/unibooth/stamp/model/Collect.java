package com.unibooth.unibooth.stamp.model;

import com.unibooth.unibooth.domain.booth.model.Booth;
import com.unibooth.unibooth.domain.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
// 스탬프 수집 모델
public class Collect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String university;

    @OneToOne(fetch = FetchType.EAGER)
    private Booth booth;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
