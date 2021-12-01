package com.unibooth.unibooth.domain.user.dto.response;

import com.unibooth.unibooth.domain.user.model.Entertainer;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ByteArrayResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Data
@Getter
@NoArgsConstructor
public class EntertainerDto {
    private String nickname;
    private byte[] image;

    public EntertainerDto(String nickname, byte[] image) {
        this.nickname = nickname;
        this.image = image;
    }

    public static EntertainerDto from(Entertainer entertainer) throws IOException {

        File file = new File(entertainer.getProfilePhoto().getFilePath() + entertainer.getProfilePhoto().getFileName());
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return new EntertainerDto(
                entertainer.getNickname(),
                resource.getByteArray());
    }
}
