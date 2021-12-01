package com.unibooth.unibooth.domain.booth.repository;

import com.unibooth.unibooth.domain.booth.model.Booth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoothRepository extends JpaRepository<Booth, Long> {

    Optional<Booth> findById(Long id);

    default Booth findByIdElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NullPointerException("해당 부스가 없어요."));
    }
}
