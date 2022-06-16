package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.ReviewOttEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewOttResponseDto { //response
    private Long ottBoardno;

    private String ottPlatformname;

    private BigDecimal ottPricepoints;

    private BigDecimal ottQualitypoints;

    private BigDecimal ottTranslationpoints;

    private BigDecimal ottServicepoints;

    private BigDecimal ottTotalpoints;

    private String ottContent;

    private String ottNickname;

    private LocalDateTime ottRegdate;

    private LocalDateTime ottModdate;

    private int recommendations;

    public ReviewOttResponseDto(ReviewOttEntity reviewOttEntity) {
        this.ottBoardno = reviewOttEntity.getOttBoardno();
        this.ottPlatformname = reviewOttEntity.getOttPlatformname();
        this.ottPricepoints = reviewOttEntity.getOttPricepoints();
        this.ottQualitypoints = reviewOttEntity.getOttQualitypoints();
        this.ottTranslationpoints = reviewOttEntity.getOttTranslationpoints();
        this.ottServicepoints = reviewOttEntity.getOttServicepoints();
        this.ottTotalpoints = reviewOttEntity.getOttTotalpoints();
        this.ottContent = reviewOttEntity.getOttContent();
        this.ottNickname = reviewOttEntity.getMember().getNickname();
        this.ottRegdate = reviewOttEntity.getOttRegdate();
        this.ottModdate = reviewOttEntity.getOttModdate();
        this.recommendations = reviewOttEntity.getRecommendations().size();
    }

    public static ReviewOttResponseDto ofEntity(ReviewOttEntity entity) {
        return new ReviewOttResponseDto(entity);
    }

    public static List<ReviewOttResponseDto> ofEntities(List<ReviewOttEntity> entities) {
        return entities.stream().map(ReviewOttResponseDto::ofEntity).collect(Collectors.toList());
    }
}
