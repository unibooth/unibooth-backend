package com.unibooth.unibooth.domain.booth.service;


import com.unibooth.unibooth.domain.booth.model.FileStream;
import com.unibooth.unibooth.domain.booth.model.Tag;
import com.unibooth.unibooth.domain.booth.repository.FileStreamRepository;
import com.unibooth.unibooth.domain.booth.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FileService {

    private final FileStreamRepository fileStreamRepository;

    @Transactional(rollbackFor = {Exception.class})
    public FileStream fileUpload(MultipartFile multipartFile) throws IOException, NoSuchAlgorithmException {

        String fileName = new MD5Generator(multipartFile.getOriginalFilename() + LocalDateTime.now()).toString();
        String filePath = System.getProperty("user.dir")+ "/contentsPhoto/";
        System.out.println("fileName = " + fileName);

        File dir = new File(filePath);
        if(!dir.exists())
            dir.mkdirs();

        FileStream fileStream = FileStream.of(
                multipartFile.getOriginalFilename(), fileName, filePath
        );

        filePath =  filePath + fileName;
        File f = new File(filePath);
        multipartFile.transferTo(f);

        fileStreamRepository.save(fileStream);


        return fileStream;
    }
}
