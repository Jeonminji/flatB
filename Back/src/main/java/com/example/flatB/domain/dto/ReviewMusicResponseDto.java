package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.ReviewMusicEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewMusicResponseDto { //response
    private Long musicBoardno;

    private String musicPlatformname;

    private BigDecimal musicPricepoints;

    private BigDecimal musicSoundpoints;

    private BigDecimal musicServicepoints;

    private BigDecimal musicTotalpoints;

    private String musicContent;

    private String musicNickname;

    private LocalDateTime musicRegdate;

    private LocalDateTime musicModdate;

    private int recommendations;

    public ReviewMusicResponseDto(ReviewMusicEntity reviewMusicEntity) {
        this.musicBoardno = reviewMusicEntity.getMusicBoardno();
        this.musicPlatformname = reviewMusicEntity.getMusicPlatformname();
        this.musicPricepoints = reviewMusicEntity.getMusicPricepoints();
        this.musicSoundpoints = reviewMusicEntity.getMusicSoundpoints();
        this.musicServicepoints = reviewMusicEntity.getMusicServicepoints();
        this.musicTotalpoints = reviewMusicEntity.getMusicTotalpoints();
        this.musicContent = reviewMusicEntity.getMusicContent();
        this.musicNickname = reviewMusicEntity.getUserEntity().getNickname();
        this.musicRegdate = reviewMusicEntity.getMusicRegdate();
        this.musicModdate = reviewMusicEntity.getMusicModdate();
        this.recommendations = reviewMusicEntity.getRecommendations().size();
    }


    public static ReviewMusicResponseDto ofEntity(ReviewMusicEntity entity) {
        return new ReviewMusicResponseDto(entity);
    }

    public static List<ReviewMusicResponseDto> ofEntities(List<ReviewMusicEntity> entities) {
        return entities.stream().map(ReviewMusicResponseDto::ofEntity).collect(Collectors.toList());
    }


}
