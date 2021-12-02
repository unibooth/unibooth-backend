package com.unibooth.unibooth.stamp.service;

import com.unibooth.unibooth.domain.booth.dto.response.StampResDto;
import com.unibooth.unibooth.domain.booth.model.Booth;
import com.unibooth.unibooth.domain.booth.repository.BoothRepository;
import com.unibooth.unibooth.domain.user.model.User;
import com.unibooth.unibooth.domain.user.repository.UserRepository;
import com.unibooth.unibooth.stamp.model.Collect;
import com.unibooth.unibooth.stamp.repository.CollectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StampService {

    private final BoothRepository boothRepository;
    private final UserRepository userRepository;
    private final CollectRepository collectRepository;

    public boolean codeValidate(Long boothId, String code) {
        Booth booth = boothRepository.findByIdElseThrow(boothId);

        String stampCode = booth.getStampCode();

        if(stampCode.equals(code)) {
            return true;
        }else {
            return false;
        }

    }

    public StampResDto getStampCountOfUser(Long userId) {
        User user = userRepository.findByIdElseThrow(userId);
        Long count = collectRepository.countByUser(user);
        return StampResDto.from(count.intValue());
    }
}
