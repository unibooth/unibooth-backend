package com.unibooth.unibooth.domain.booth.controller;

import com.unibooth.unibooth.domain.booth.dto.PostingDto;
import com.unibooth.unibooth.domain.booth.dto.PostingListDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/posting/")
public class PostingController {


    @PostMapping("/{boothId}")
    public String boothPosting(@PathVariable Long boothId,
                               @ModelAttribute PostingListDto postingDto
                               ) {

        postingDto.getPostingDtoList()
                .stream().forEach(
                        postingDto1 -> System.out.println("postingDto1.getPhoto().getOriginalFilename() = " + postingDto1.getPhoto().getOriginalFilename())
                );

        return "success";
    }
}
