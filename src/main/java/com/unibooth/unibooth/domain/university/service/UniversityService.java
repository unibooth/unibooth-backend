package com.unibooth.unibooth.domain.university.service;

import com.unibooth.unibooth.domain.university.model.University;
import com.unibooth.unibooth.domain.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public University saveAndGetUniversity(String univ) {
       Optional<University> universityOptional =  universityRepository.findByName(univ);

       if(universityOptional.isPresent()) {
           return universityOptional.get();
       } else {
           University university = University.of(univ);
           universityRepository.save(university);
           return university;
       }
    }


}
