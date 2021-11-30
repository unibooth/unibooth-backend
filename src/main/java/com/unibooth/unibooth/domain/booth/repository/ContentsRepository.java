package com.unibooth.unibooth.domain.booth.repository;

import com.unibooth.unibooth.domain.booth.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentsRepository extends JpaRepository<Content, Long> {
}
