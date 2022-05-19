package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.ReviewMusicEntity;
import com.example.flatB.domain.entity.UserEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewMusicSaveDto { //save request
    private String musicPlatformname;

    private BigDecimal musicPricepoints;

    private BigDecimal musicSoundpoints;

    private BigDecimal musicServicepoints;

    private BigDecimal musicTotalpoints;

    private String musicContent;

    private UserEntity userEntity;

    private LocalDateTime musicRegdate;

    public ReviewMusicEntity toEntity() {
        ReviewMusicEntity reviewMusicEntity = ReviewMusicEntity.builder()
                .musicPlatformname(musicPlatformname)
                .musicPricepoints(musicPricepoints)
                .musicSoundpoints(musicSoundpoints)
                .musicServicepoints(musicServicepoints)
                .musicTotalpoints(musicTotalpoints)
                .musicContent(musicContent)
                .userEntity(userEntity)
                .musicRegdate(LocalDateTime.now())
                .build();
        return reviewMusicEntity;
    }
}
