package com.unibooth.unibooth.domain.booth.model;

import com.unibooth.unibooth.domain.booth.dto.request.TagDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private int price;

    public Tag(String item, int price, int positionX, int positionY) {
        this.item = item;
        this.price = price;
        this.positionX = positionX;
        this.positionY = positionY;
    }
    private int positionX;
    private int positionY;

    public static Tag of(TagDto tagDto) {
        return new Tag(tagDto.getItem(), tagDto.getPrice(), tagDto.getPositionX(), tagDto.getPositionY());
    }
}
