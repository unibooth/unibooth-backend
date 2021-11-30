package com.unibooth.unibooth.domain.booth.model;

import com.unibooth.unibooth.domain.booth.dto.TagDto;
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

    public Tag(String item, int price) {
        this.item = item;
        this.price = price;
    }

    public static Tag of(TagDto tagDto) {
        return new Tag(tagDto.getItem(), tagDto.getPrice());
    }
}
