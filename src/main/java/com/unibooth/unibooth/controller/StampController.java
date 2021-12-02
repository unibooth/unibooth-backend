package com.unibooth.unibooth.controller;


import com.unibooth.unibooth.domain.booth.service.StampService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/stamp/")

public class StampController {

    private final StampService stampService;

    @PostMapping("/{boothId}")
    public boolean stampValidate(@PathVariable Long boothId, @RequestParam String code) {
        return stampService.codeValidate(boothId, code);
    }
}
