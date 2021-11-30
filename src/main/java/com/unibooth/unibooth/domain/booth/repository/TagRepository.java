package com.unibooth.unibooth.domain.booth.repository;

import com.unibooth.unibooth.domain.booth.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
