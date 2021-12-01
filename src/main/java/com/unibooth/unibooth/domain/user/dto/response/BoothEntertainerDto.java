package com.unibooth.unibooth.domain.user.dto.response;

import com.unibooth.unibooth.domain.booth.model.Booth;
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
import java.util.Date;

@Data
@Getter
@NoArgsConstructor
public class BoothEntertainerDto {
    private int locationNum;
    private Date operateDate;
    private String name;
    private byte[] profilePhoto;

    public BoothEntertainerDto(int locationNum, Date date, String name, byte[] byteArray) {
        this.locationNum = locationNum;
        this.operateDate = date;
        this.name = name;
        this.profilePhoto = byteArray;
    }


    public static BoothEntertainerDto from(Booth booth, Entertainer entertainer) throws IOException {
        File file = new File(entertainer.getProfilePhoto().getFilePath() + entertainer.getProfilePhoto().getFileName());
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return new BoothEntertainerDto(
                booth.getLocationNum(),
                booth.getDate(),
                entertainer.getName(),
                resource.getByteArray());
    }
}
