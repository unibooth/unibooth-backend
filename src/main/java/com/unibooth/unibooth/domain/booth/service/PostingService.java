package com.unibooth.unibooth.domain.booth.service;


import com.unibooth.unibooth.domain.booth.dto.request.ContentDto;
import com.unibooth.unibooth.domain.booth.dto.request.PostingListDto;
import com.unibooth.unibooth.domain.booth.dto.response.*;
import com.unibooth.unibooth.domain.booth.model.Content;
import com.unibooth.unibooth.domain.booth.model.FileStream;
import com.unibooth.unibooth.domain.booth.model.Posting;
import com.unibooth.unibooth.domain.booth.model.Tag;
import com.unibooth.unibooth.domain.booth.repository.ContentsRepository;
import com.unibooth.unibooth.domain.booth.repository.PostingRepository;
import com.unibooth.unibooth.domain.booth.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = {Exception.class})
    public void boothPosting(PostingListDto postingListDto) throws IOException, NoSuchAlgorithmException {

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
                postingListDto.getPostingTitle(),
                coverPhoto,
                contentsList
        );

        postingRepository.save(posting);

    }

    public PostingResDto getPostingDetail(Long boothId) throws IOException {
            Posting posting = postingRepository.findByIdElseThrow(boothId);
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
                PhotoFileDto photoFileDto = PhotoFileDto.from(
                        resource.getByteArray(),
                        tagResDtos
                );
                ContentResDto contentResDto = ContentResDto.from(
                        contents.getId(),
                        photoFileDto,
                        contents.getContents(),
                        contents.getContentTitle()
                );

                contentResDtos.add(contentResDto);
            }
            File file = new File(posting.getCoverPhoto().getFilePath() + posting.getCoverPhoto().getFileName());
            Path path = Paths.get(file.getAbsolutePath());
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            PostingResDto postingResDto =
                    PostingResDto.from(
                            posting.getId(),
                            posting.getPostingTitle(),
                            resource.getByteArray(),
                            contentResDtos,
                            posting.getLikeUsers().size()
                    );


        return postingResDto;
    }

    public List<PostingApproxDto> getAllPosting() {
        List<Posting> postingList = postingRepository.findAll();

        List<PostingApproxDto> postingResDtoList =
                postingList.stream().map(
                        posting -> {
                            File file = new File(posting.getCoverPhoto().getFilePath() + posting.getCoverPhoto().getFileName());
                            Path path = Paths.get(file.getAbsolutePath());
                            ByteArrayResource resource = null;
                            try {
                                resource = new ByteArrayResource(Files.readAllBytes(path));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return PostingApproxDto.from(
                                    posting.getId(),
                                    posting.getPostingTitle(),
                                    resource.getByteArray(),
                                    posting.getLikeUsers().size()
                                );

                        }
                ).collect(Collectors.toList());

        return postingResDtoList;
    }

}
