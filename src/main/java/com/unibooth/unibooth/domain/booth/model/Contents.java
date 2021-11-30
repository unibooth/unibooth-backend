package com.unibooth.unibooth.domain.booth.model;

import com.unibooth.unibooth.domain.booth.dto.ContentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ColumnDefault("")
    private String contentTitle;

    @Column(length = 3000)
    @ColumnDefault("")
    private String contents;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FileStream fileStream;


    public Contents(String contentTitle,
                    String contents,
                    FileStream fileStream) {

        this.contentTitle = contentTitle;
        this.contents = contents;
        this.fileStream = fileStream;

    }

    public static Contents of(
            String contentTitle,
            String contents,
            FileStream fileStream
            ) {
        return new Contents(contentTitle, contents, fileStream);
    }

}
