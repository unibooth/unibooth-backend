package com.unibooth.unibooth.domain.booth.service;

import com.unibooth.unibooth.domain.booth.dto.request.BoothCreateDto;
import com.unibooth.unibooth.domain.booth.model.Booth;
import com.unibooth.unibooth.domain.booth.repository.BoothRepository;
import com.unibooth.unibooth.domain.user.model.Entertainer;
import com.unibooth.unibooth.domain.user.repository.EntertainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoothService {
    private final BoothRepository boothRepository;
    private final EntertainerRepository entertainerRepository;

    public void boothCreate(BoothCreateDto boothCreateDto, Long enterId) {
        Entertainer entertainer = entertainerRepository.findByIdElseThrow(enterId);
        Booth booth = Booth.of(
                entertainer,
                boothCreateDto.getUniversity(),
                boothCreateDto.getLocation(),
                boothCreateDto.getType(),
                boothCreateDto.getDate()
        );

        boothRepository.save(booth);
    }

}
