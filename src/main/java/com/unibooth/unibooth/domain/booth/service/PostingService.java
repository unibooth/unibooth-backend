package com.unibooth.unibooth.domain.booth.service;


import com.unibooth.unibooth.domain.booth.dto.PostingListDto;
import org.springframework.transaction.annotation.Transactional;

public class PostingService {

    @Transactional(rollbackFor = {Exception.class})
    public void boothPosting(PostingListDto postingListDto) {


    }
}
