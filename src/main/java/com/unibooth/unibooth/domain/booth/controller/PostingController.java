package com.unibooth.unibooth.domain.booth.controller;

import com.unibooth.unibooth.domain.booth.dto.ContentDto;
import com.unibooth.unibooth.domain.booth.dto.PostingListDto;
import com.unibooth.unibooth.domain.booth.dto.TagDto;
import com.unibooth.unibooth.domain.booth.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/posting/")
public class PostingController {

    private final PostingService postingService;

    @PostMapping("/{boothId}")
    public String boothPosting(@PathVariable Long boothId,
                               @ModelAttribute PostingListDto postingDto
                               ) throws IOException, NoSuchAlgorithmException {
        System.out.println("postingDto.getPostingTitle() = " + postingDto.getPostingTitle());

        postingService.boothPosting(postingDto);
        return "success";
    }


}
