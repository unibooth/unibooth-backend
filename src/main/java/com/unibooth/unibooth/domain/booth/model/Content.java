package com.unibooth.unibooth.domain.booth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contentTitle;

    @Column(length = 3000)
    private String contents;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FileStream fileStream;


    public Content(String contentTitle,
                   String contents,
                   FileStream fileStream) {

        this.contentTitle = contentTitle;
        this.contents = contents;
        this.fileStream = fileStream;
    }

    public static Content of(
            String contentTitle,
            String contents,
            FileStream fileStream
            ) {
        return new Content(contentTitle, contents, fileStream);
    }

}
