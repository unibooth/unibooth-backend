package com.unibooth.unibooth.domain.booth.service;

import com.unibooth.unibooth.domain.booth.model.Posting;
import com.unibooth.unibooth.domain.booth.repository.PostingRepository;
import com.unibooth.unibooth.domain.user.model.User;
import com.unibooth.unibooth.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostingRepository postingRepository;
    private final UserRepository userRepository;

    public void likeAppendOrDelete(Long postingId, Long userId) {
        Posting posting = postingRepository.findByIdElseThrow(postingId);
        User user  = userRepository.findByIdElseThrow(userId);

        if(posting.getLikeUsers().contains(user)) {
            posting.getLikeUsers().remove(user);
        }else {
            posting.getLikeUsers().add(user);
        }

    }
}
