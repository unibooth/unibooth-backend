package com.unibooth.unibooth.domain.booth.service;


import com.unibooth.unibooth.domain.booth.dto.request.CommentDto;
import com.unibooth.unibooth.domain.booth.dto.request.ContentDto;
import com.unibooth.unibooth.domain.booth.dto.request.PostingListDto;
import com.unibooth.unibooth.domain.booth.dto.response.*;
import com.unibooth.unibooth.domain.booth.model.*;
import com.unibooth.unibooth.domain.booth.repository.*;
import com.unibooth.unibooth.domain.user.dto.response.EntertainerDto;
import com.unibooth.unibooth.domain.user.model.Entertainer;
import com.unibooth.unibooth.domain.user.model.User;
import com.unibooth.unibooth.domain.user.repository.EntertainerRepository;
import com.unibooth.unibooth.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostingService {

    private final FileService fileService;
    private final PostingRepository postingRepository;
    private final TagRepository tagRepository;
    private final ContentsRepository contentsRepository;
    private final BoothRepository boothRepository;

    private final CommentRepository commentRepository;
    private final EntertainerRepository entertainerRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = {Exception.class})
    public void boothPosting(Long boothId, PostingListDto postingListDto) throws IOException, NoSuchAlgorithmException {
        Booth booth = boothRepository.findByIdElseThrow(boothId);
        Entertainer entertainer = booth.getEntertainer();

        List<Content> contentsList = new ArrayList<>();
        for(int i=0; i<postingListDto.getContentDtoList().size(); i++) {
            ContentDto contentDto = postingListDto.getContentDtoList().get(i);
            List<Tag> tagList = new ArrayList<>();
            for(int j=0; j<contentDto.getTagDtoList().size(); j++) {
                Tag tag  = Tag.of(contentDto.getTagDtoList().get(j));
                tagRepository.save(tag);
                tagList.add(tag);
            }

            FileStream fileStream = fileService.fileUpload(contentDto.getPhoto());
            fileStream.setTagList(tagList);
            Content contents = Content.of(
                    contentDto.getContentTitle(),
                    contentDto.getContents(),
                    fileStream
            );

            contentsRepository.save(contents);
            contentsList.add(contents);
        }

        FileStream coverPhoto = fileService.fileUpload(postingListDto.getCoverPhoto());
        Posting posting = Posting.of(
                booth,
                entertainer,
                postingListDto.getPostingTitle(),
                coverPhoto,
                contentsList,
                booth.getUniversity()
        );
        postingRepository.save(posting);
    }

    public PostingResDto getPostingDetail(Long postId) throws IOException {
            Posting posting = postingRepository.findByIdElseThrow(postId);
            Booth booth = posting.getBooth();
        System.out.println("booth.getLocation() = " + booth.getLocation());
            Entertainer entertainer = posting.getEntertainer();
            List<Comment> commentList = commentRepository.findByPostingId(postId);

            List<CommentResDto> commentResDtoList = new ArrayList<>();
            for(int i=0; i<commentList.size(); i++) {
                Comment comment = commentList.get(i);
                File file = new File(comment.getFileStream().getFilePath() + comment.getFileStream().getFileName());
                Path path = Paths.get(file.getAbsolutePath());
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

                CommentResDto commentResDto = CommentResDto.from(
                        comment.getId(),
                        comment.getCreatedAt(),
                        comment.getContent(),
                        comment.getUser(),
                        resource.getByteArray()
                );

                commentResDtoList.add(commentResDto);
            }
//
            List<ContentResDto> contentResDtos = new ArrayList<>();
            for(int j=0; j<posting.getContentsList().size(); j++) {
                Content contents = posting.getContentsList().get(j);

                List<TagResDto> tagResDtos = new ArrayList<>();
                for(int k=0; k<contents.getFileStream().getTagList().size(); k++) {
                    TagResDto tagResDto = TagResDto.from(contents.getFileStream().getTagList().get(k));
                    tagResDtos.add(tagResDto);
                }
                File file = new File(contents.getFileStream().getFilePath() + contents.getFileStream().getFileName());
                Path path = Paths.get(file.getAbsolutePath());
                ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

                ContentResDto contentResDto = ContentResDto.from(
                        contents.getId(),
                        resource.getByteArray(),
                        tagResDtos,
                        contents.getContents(),
                        contents.getContentTitle()
                );
                contentResDtos.add(contentResDto);
            }
            File file = new File(posting.getCoverPhoto().getFilePath() + posting.getCoverPhoto().getFileName());
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            EntertainerDto entertainerDto = EntertainerDto.from(entertainer);

            PostingResDto postingResDto =
                    PostingResDto.from(
                            posting.getId(),
                            booth.getType(),
                            entertainerDto,
                            posting.getPostingTitle(),
                            resource.getByteArray(),
                            booth.getLocation(),
                            booth.getDate(),
                            contentResDtos,
                            posting.getLikeUsers().size(),
                            commentResDtoList,
                            booth.getUniversity()
                    );

        return postingResDto;
    }

    public List<PostingApproxDto> getPostingByUniv(String university) {
        List<Posting> postingList = postingRepository.findByUniversity(university);

        List<PostingApproxDto> postingResDtoList =
                postingList.stream().map(
                        posting -> {
                            List<CommentResDto> comments = commentRepository.findByPostingId(posting.getId())
                                    .stream().map(
                                            comment ->  {
                                                File file = new File(comment.getFileStream().getFilePath() + comment.getFileStream().getFileName());
                                                Path path = Paths.get(file.getAbsolutePath());
                                                ByteArrayResource resource = null;
                                                try {
                                                    resource = new ByteArrayResource(Files.readAllBytes(path));
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                return CommentResDto.from(
                                                    comment.getId(),
                                                    comment.getCreatedAt(),
                                                    comment.getContent(),
                                                    comment.getUser(),
                                                        resource.getByteArray()
                                            );}
                                    ).collect(Collectors.toList());

                            File file = new File(posting.getCoverPhoto().getFilePath() + posting.getCoverPhoto().getFileName());
                            Path path = Paths.get(file.getAbsolutePath());
                            ByteArrayResource resource = null;
                            EntertainerDto entertainerDto = null;

                            try {
                                resource = new ByteArrayResource(Files.readAllBytes(path));
                                entertainerDto = EntertainerDto.from(posting.getEntertainer());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            return PostingApproxDto.from(
                                    posting.getId(),
                                    posting.getPostingTitle(),
                                    resource.getByteArray(),
                                    posting.getLikeUsers().size(),
                                    posting.getBooth().getType(),
                                    comments,
                                    posting.getBooth().getLatitude(),
                                    posting.getBooth().getLongitude(),
                                    entertainerDto,
                                    posting.getBooth().getLocation()

                            );

                        }
                ).collect(Collectors.toList());

        return postingResDtoList;
    }

    public void addComment(Long postId, Long userId, String com, MultipartFile image) throws IOException, NoSuchAlgorithmException {
        FileStream fileStream = fileService.fileUpload(image);
        Posting posting = postingRepository.findByIdElseThrow(postId);
        User user = userRepository.findByIdElseThrow(userId);
        Comment comment = Comment.of(com, user, posting, fileStream);
        commentRepository.save(comment);
    }

}
