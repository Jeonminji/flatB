package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.ReviewMusicEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ReviewMusicUpdateDto { //update request
    private String musicPlatformname;

    private BigDecimal musicPricepoints;

    private BigDecimal musicSoundpoints;

    private BigDecimal musicServicepoints;

    private BigDecimal musicTotalpoints;

    private String musicContent;

    private LocalDateTime musicModdate;

    @Builder
    public ReviewMusicUpdateDto(String musicPlatformname, BigDecimal musicPricepoints, BigDecimal musicSoundpoints,
                                BigDecimal musicServicepoints, BigDecimal musicTotalpoints, String musicContent,
                                LocalDateTime musicModdate) {
        this.musicPlatformname = musicPlatformname;
        this.musicPricepoints = musicPricepoints;
        this.musicSoundpoints = musicSoundpoints;
        this.musicServicepoints = musicServicepoints;
        this.musicTotalpoints = musicTotalpoints;
        this.musicContent = musicContent;
        this.musicModdate = LocalDateTime.now();
    }

    public ReviewMusicUpdateDto(ReviewMusicEntity reviewMusicEntity) {
        this.musicPlatformname = reviewMusicEntity.getMusicPlatformname();
        this.musicPricepoints = reviewMusicEntity.getMusicPricepoints();
        this.musicSoundpoints = reviewMusicEntity.getMusicSoundpoints();
        this.musicServicepoints = reviewMusicEntity.getMusicServicepoints();
        this.musicTotalpoints = reviewMusicEntity.getMusicTotalpoints();
        this.musicContent = reviewMusicEntity.getMusicContent();
        this.musicModdate = reviewMusicEntity.getMusicModdate();
    }

    public static ReviewMusicUpdateDto ofEntity(ReviewMusicEntity entity) {
        return new ReviewMusicUpdateDto(entity);
    }
}
