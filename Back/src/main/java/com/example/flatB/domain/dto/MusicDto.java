package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.MusicEntity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MusicDto {
    String musicName;
    String musicLogo;

    public MusicDto(MusicEntity musicEntity) {
        this.musicName = musicEntity.getMusicName();
        this.musicLogo = musicEntity.getMusicLogo();
    }

    public static MusicDto ofEntity(MusicEntity musicEntity) {
        return new MusicDto(musicEntity);
    }
}
