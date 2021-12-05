package com.unibooth.unibooth.domain.booth.dto.response;

import com.unibooth.unibooth.domain.booth.model.FileStream;
import com.unibooth.unibooth.domain.user.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Getter
public class CommentResDto {
    private Long id;
    private Date createdAt;
    private String content;
    private User user;
    private byte[] image;

    public CommentResDto(Long id, Date createdAt, String content, User user, byte[] image) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.image = image;
    }


    public static CommentResDto from(Long id, Date createdAt, String content, User user, byte[] image) {
        return new CommentResDto(id, createdAt, content, user, image);
    }
}
