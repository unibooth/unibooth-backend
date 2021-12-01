package com.unibooth.unibooth.controller;


import com.unibooth.unibooth.domain.booth.dto.request.BoothCreateDto;
import com.unibooth.unibooth.domain.booth.service.BoothService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booth/")
public class BoothController {

    private final BoothService boothService;
    @PostMapping("/{enterId}")
    public String boothCreate(@PathVariable Long enterId, @RequestBody BoothCreateDto boothCreateDto) {
        boothService.boothCreate(boothCreateDto, enterId);
        return "success";
    }
}
