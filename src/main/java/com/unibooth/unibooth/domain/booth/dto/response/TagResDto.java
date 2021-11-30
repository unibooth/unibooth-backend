package com.unibooth.unibooth.domain.booth.dto.response;


import com.unibooth.unibooth.domain.booth.model.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class TagResDto {

    private Long id;
    private String item;
    private int price;
    private int positionX;
    private int positionY;

    public TagResDto(Long id, String item, int price, int positionX, int positionY) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static TagResDto from(Tag tag) {

        return new TagResDto(
                tag.getId(),
                tag.getItem(),
                tag.getPrice(),
                tag.getPositionX(),
                tag.getPositionY()
        );
    }
}
