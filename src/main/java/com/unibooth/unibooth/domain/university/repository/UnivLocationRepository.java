package com.unibooth.unibooth.domain.university.repository;

import com.unibooth.unibooth.domain.university.model.UnivLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnivLocationRepository extends JpaRepository<UnivLocation, Long> {

    UnivLocation findByUniversity(String university);

}
