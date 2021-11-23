package com.unibooth.unibooth.domain.university.repository;

import com.unibooth.unibooth.domain.university.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    Optional<University> findByName(String name);
}
