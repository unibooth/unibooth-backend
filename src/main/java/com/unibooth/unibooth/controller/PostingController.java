package com.unibooth.unibooth.controller;

import com.unibooth.unibooth.domain.booth.dto.request.CommentDto;
import com.unibooth.unibooth.domain.booth.dto.request.PostingListDto;
import com.unibooth.unibooth.domain.booth.dto.response.PostingApproxDto;
import com.unibooth.unibooth.domain.booth.dto.response.PostingResDto;
import com.unibooth.unibooth.domain.booth.repository.CommentRepository;
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

    @PostMapping("/{boothId}/{enterId}")
    @ResponseStatus(HttpStatus.OK)
    public String boothPosting(@PathVariable Long boothId,
                               @PathVariable Long enterId,
                               @ModelAttribute PostingListDto postingDto
                               ) throws IOException, NoSuchAlgorithmException {
        System.out.println("postingDto.getPostingTitle() = " + postingDto.getPostingTitle());
        postingService.boothPosting(boothId, enterId, postingDto);
        return "success";
    }

    @PostMapping("/like/{postingId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String likeAppendOrRemove(@PathVariable Long postingId, @PathVariable Long userId) {
        likeService.likeAppendOrDelete(postingId, userId);
        return "success";
    }

    @GetMapping("/detail/{postId}/{enterId}")
    @ResponseStatus(HttpStatus.OK)
    public PostingResDto getBoothDetail(@PathVariable Long postId, @PathVariable Long enterId) throws IOException {
        return postingService.getPostingDetail(postId, enterId);
    }

    @GetMapping("/univ")
    @ResponseStatus(HttpStatus.OK)
    public List<PostingApproxDto> getAllBoothPosting(@RequestParam String university) {
        System.out.println("university = " + university);
        return postingService.getPostingByUniv(university);
    }


    @PostMapping("/comment/{postId}/{userId}")
    public String addComment(@PathVariable Long postId, @PathVariable Long userId, @RequestBody CommentDto commentDto) {
        postingService.addComment(postId, userId, commentDto);
        return "success";
    }


}
