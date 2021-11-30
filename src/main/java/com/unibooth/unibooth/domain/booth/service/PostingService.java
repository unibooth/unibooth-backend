package com.unibooth.unibooth.domain.booth.service;


import com.unibooth.unibooth.domain.booth.dto.request.ContentDto;
import com.unibooth.unibooth.domain.booth.dto.request.PostingListDto;
import com.unibooth.unibooth.domain.booth.model.Contents;
import com.unibooth.unibooth.domain.booth.model.FileStream;
import com.unibooth.unibooth.domain.booth.model.Posting;
import com.unibooth.unibooth.domain.booth.model.Tag;
import com.unibooth.unibooth.domain.booth.repository.ContentsRepository;
import com.unibooth.unibooth.domain.booth.repository.PostingRepository;
import com.unibooth.unibooth.domain.booth.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingService {

    private final FileService fileService;
    private final PostingRepository postingRepository;
    private final TagRepository tagRepository;
    private final ContentsRepository contentsRepository;

    @Transactional(rollbackFor = {Exception.class})
    public void boothPosting(PostingListDto postingListDto) throws IOException, NoSuchAlgorithmException {

        List<Contents> contentsList = new ArrayList<>();
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
            Contents contents = Contents.of(
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

    public

    public
}
