package com.example.flatB.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "compare_music")
public class CompareMusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String musicName;

    private String musicPlan;

    private String musicPlandescription;

    private String musicPrice;

    private String musicDiscount;

    private String musicEtc;

    @Builder
    public CompareMusicEntity(Long id, String musicName, String musicPlan, String musicPlandescription,
                              String musicPrice, String musicDiscount, String musicEtc) {
        this.id = id;
        this.musicName = musicName;
        this.musicPlan = musicPlan;
        this.musicPlandescription = musicPlandescription;
        this.musicPrice = musicPrice;
        this.musicDiscount = musicDiscount;
        this.musicEtc = musicEtc;
    }
}
