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
@Table(name = "music")
public class MusicEntity {
    @Id
    private String musicName;

    private String musicLogo;

    @Builder
    public MusicEntity(String musicName, String musicLogo) {
        this.musicName = musicName;
        this.musicLogo = musicLogo;
    }
}
