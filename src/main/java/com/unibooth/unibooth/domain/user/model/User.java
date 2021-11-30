package com.unibooth.unibooth.domain.user.model;

import com.unibooth.unibooth.domain.booth.model.Posting;
import com.unibooth.unibooth.domain.university.model.University;
import com.unibooth.unibooth.domain.user.dto.UserCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class User implements UserDetails {

    public User(String email, String name, University university) {
        this.email = email;
        this.name = name;
        this.point = 0L;
        this.university = university;
        this.isUniversityCertified = false;
        this.roles = Collections.singletonList("ROLE_USER");

    }

    public static User of(UserCreateDto userCreateDto, University university) {
        return new User(userCreateDto.getEmail(), userCreateDto.getName(), university);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;
    private String name;

    private String phoneNumber;

    private Long point;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private University university;

    private boolean isUniversityCertified;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Entertainer entertainerProfile;

    private boolean isEntertainer;

    @Getter
    @ManyToMany(mappedBy = "likeUsers")
    private Set<Posting> likePostings = new HashSet<>();


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 위시 부스, 위시 게시물, 즐겨찾기 엔터테이너, 즐겨찾기 대학, 스탬프 리스트



}
