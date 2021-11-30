package com.unibooth.unibooth.domain.booth.repository;

import com.unibooth.unibooth.domain.booth.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {


    Optional<Posting> findById(Long id);

    default Posting findByIdElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new NullPointerException("게시물 아이디를 찾을 수 없어요."));
    }
}
