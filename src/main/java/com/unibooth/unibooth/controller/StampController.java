package com.unibooth.unibooth.controller;


import com.unibooth.unibooth.domain.booth.dto.response.StampResDto;
import com.unibooth.unibooth.stamp.service.StampService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/stamp/")

public class StampController {

    private final StampService stampService;

    @PostMapping("/{boothId}/{userId}")
    public boolean stampValidate(@PathVariable Long boothId,@PathVariable Long userId, @RequestParam String code) {
        return stampService.codeValidateAndSave(boothId, userId, code);
    }

    @GetMapping("/user/{userId}")
    public StampResDto getStampCountOfUser(@PathVariable Long userId) {
        return stampService.getStampCountOfUser(userId);
    }
}
