package com.unibooth.unibooth.domain.booth.service;

import com.unibooth.unibooth.domain.booth.model.Booth;
import com.unibooth.unibooth.domain.booth.repository.BoothRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StampService {

    private final BoothRepository boothRepository;
    public boolean codeValidate(Long boothId, String code) {
        Booth booth = boothRepository.findByIdElseThrow(boothId);

        String stampCode = booth.getStampCode();

        if(stampCode.equals(code)) {
            return true;
        }else {
            return false;
        }

    }
}
