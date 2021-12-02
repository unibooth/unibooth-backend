package com.unibooth.unibooth.domain.booth.repository;

import com.unibooth.unibooth.domain.booth.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
