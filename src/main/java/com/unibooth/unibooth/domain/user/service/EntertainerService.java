package com.unibooth.unibooth.domain.user.service;

import com.unibooth.unibooth.domain.booth.model.FileStream;
import com.unibooth.unibooth.domain.booth.service.FileService;
import com.unibooth.unibooth.domain.user.dto.request.EnterCreateDto;
import com.unibooth.unibooth.domain.user.model.Entertainer;
import com.unibooth.unibooth.domain.user.repository.EntertainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class EntertainerService {
    private final EntertainerRepository entertainerRepository;
    private final FileService fileService;

    public void registerEntertainer(EnterCreateDto enterCreateDto) throws IOException, NoSuchAlgorithmException {
        FileStream fileStream = fileService.fileUpload(enterCreateDto.getProfilePhoto());
        Entertainer entertainer = Entertainer.of(
                enterCreateDto.getName(),
                enterCreateDto.getIntroduce(),
                fileStream
        );
        entertainerRepository.save(entertainer);
    }
}
