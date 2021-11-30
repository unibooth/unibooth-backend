package com.unibooth.unibooth.domain.booth.controller;

import com.unibooth.unibooth.domain.booth.dto.PostingListDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/posting/")
public class PostingController {


    @PostMapping("/{boothId}")
    public String boothPosting(@PathVariable Long boothId,
                               @ModelAttribute PostingListDto postingDto
                               ) {
        System.out.println("postingDto.getPostingTitle() = " + postingDto.getPostingTitle());
        postingDto.getContentDtoList()
                .stream().forEach(
                        contentDto1 -> System.out.println("postingDto1.getPhoto().getOriginalFilename() = " + contentDto1.getPhoto().getOriginalFilename())
                );

        return "success";
    }
}
