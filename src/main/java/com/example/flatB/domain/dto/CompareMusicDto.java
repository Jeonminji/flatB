package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.CompareMusicEntity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompareMusicDto {
    private String musicName;

    private String musicPlan;

    private String musicPlandescription;

    private String musicPrice;

    private String musicDiscount;

    private String musicEtc;

    public CompareMusicEntity toEntity() {
        CompareMusicEntity compareMusicEntity = CompareMusicEntity.builder()
                .musicName(musicName)
                .musicPlan(musicPlan)
                .musicPlandescription(musicPlandescription)
                .musicPrice(musicPrice)
                .musicDiscount(musicDiscount)
                .musicEtc(musicEtc)
                .build();
        return compareMusicEntity;
    }
}
