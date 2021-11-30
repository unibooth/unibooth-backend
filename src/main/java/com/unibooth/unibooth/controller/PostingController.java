package com.unibooth.unibooth.controller;

import com.unibooth.unibooth.domain.booth.dto.request.PostingListDto;
import com.unibooth.unibooth.domain.booth.dto.response.PostingResDto;
import com.unibooth.unibooth.domain.booth.service.LikeService;
import com.unibooth.unibooth.domain.booth.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/posting/")
public class PostingController {

    private final PostingService postingService;
    private final LikeService likeService;

    @PostMapping("/{boothId}")
    @ResponseStatus(HttpStatus.OK)
    public String boothPosting(@PathVariable Long boothId,
                               @ModelAttribute PostingListDto postingDto
                               ) throws IOException, NoSuchAlgorithmException {
        System.out.println("postingDto.getPostingTitle() = " + postingDto.getPostingTitle());
        postingService.boothPosting(postingDto);
        return "success";
    }

    @PostMapping("/like/{postingId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String likeAppendOrRemove(@PathVariable Long postingId, @PathVariable Long userId) {
        likeService.likeAppendOrDelete(postingId, userId);
        return "success";
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PostingResDto> getAllBoothPosting() throws IOException {
        return postingService.getAllPosting();
    }




}
