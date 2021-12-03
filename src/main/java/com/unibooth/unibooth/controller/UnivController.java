package com.unibooth.unibooth.controller;

import com.unibooth.unibooth.domain.university.dto.UnivLocationDto;
import com.unibooth.unibooth.domain.university.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/univ")
public class UnivController {
    private final UniversityService universityService;

    @PostMapping("/location")
    public String registerLocation(@RequestBody UnivLocationDto univLocationDto) {
        universityService.univLocRegister(univLocationDto);
        return "success";
    }

    @GetMapping("/location")
    public UnivLocationDto getLocation(@RequestParam String university) {
        UnivLocationDto location = universityService.getLocation(university);
        return location;
    }
}
