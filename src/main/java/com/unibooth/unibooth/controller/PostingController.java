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
import org.springframework.web.multipart.MultipartFile;

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
        postingService.boothPosting(boothId, postingDto);
        return "success";
    }

    @PostMapping("/like/{postingId}/{userId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseStatus(HttpStatus.OK)
    public String likeAppendOrRemove(@PathVariable Long postingId, @PathVariable Long userId) {
        likeService.likeAppendOrDelete(postingId, userId);
        return "success";
    }

    @GetMapping("/detail/{postId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseStatus(HttpStatus.OK)
    public PostingResDto getBoothDetail(@PathVariable Long postId) throws IOException {
        return postingService.getPostingDetail(postId);
    }

    @GetMapping("/univ")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseStatus(HttpStatus.OK)
    public List<PostingApproxDto> getAllBoothPosting(@RequestParam String university) {
        System.out.println("university = " + university);
        return postingService.getPostingByUniv(university);
    }


    @PostMapping("/comment/{postId}/{userId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public String addComment(@PathVariable Long postId, @PathVariable Long userId, @RequestParam MultipartFile image,  @RequestParam String comment) throws IOException, NoSuchAlgorithmException {
        postingService.addComment(postId, userId, comment, image);
        return "success";
    }


}
