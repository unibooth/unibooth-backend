package com.unibooth.unibooth.domain.user.repository;

import com.unibooth.unibooth.domain.user.model.Entertainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntertainerRepository extends JpaRepository<Entertainer, Long> {

    Optional<Entertainer> findById(Long id);

    default Entertainer findByIdElseThrow(Long id) {
        return findById(id).orElseThrow(()-> new NullPointerException("엔터테이너가 없습니다."));
    }
}
