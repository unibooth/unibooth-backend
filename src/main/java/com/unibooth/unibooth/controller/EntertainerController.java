package com.unibooth.unibooth.controller;

import com.unibooth.unibooth.domain.user.dto.request.EnterCreateDto;
import com.unibooth.unibooth.domain.user.service.EntertainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entertainer")
public class EntertainerController {
    private final EntertainerService entertainerService;

    @PostMapping
    public String registerEntertainer(@ModelAttribute EnterCreateDto enterCreateDto) throws IOException, NoSuchAlgorithmException {
        entertainerService.registerEntertainer(enterCreateDto);
        return "success";
    }
}
