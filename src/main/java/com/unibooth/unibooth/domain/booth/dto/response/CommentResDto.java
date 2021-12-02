package com.unibooth.unibooth.domain.booth.dto.response;

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

    public CommentResDto(Long id, Date createdAt, String content, User user) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }


    public static CommentResDto from(Long id, Date createdAt, String content, User user) {
        return new CommentResDto(id, createdAt, content, user);
    }
}
