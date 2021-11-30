package com.unibooth.unibooth.domain.booth.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FileStream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFileName;
    private String fileName;
    private String filePath;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tagList;

    public FileStream(
            String originalFileName,
            String fileName,
            String filePath,
            List<Tag> tagList) {

        this.originalFileName = originalFileName;
        this.fileName = fileName;
        this.filePath = filePath;
        this.tagList = tagList;
    }

    public FileStream(
            String originalFileName,
            String fileName,
            String filePath) {

        this.originalFileName = originalFileName;
        this.fileName = fileName;
        this.filePath = filePath;

    }

    public static FileStream of(String originalFileName, String fileName, String filePath, List<Tag> tagList) {
        return new FileStream(originalFileName, fileName, filePath, tagList);
    }

    public static FileStream of(String originalFileName, String fileName, String filePath) {
        return new FileStream(originalFileName, fileName, filePath);
    }


}