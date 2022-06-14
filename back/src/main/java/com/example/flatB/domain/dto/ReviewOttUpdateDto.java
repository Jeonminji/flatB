package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.ReviewOttEntity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Setter
public class ReviewOttUpdateDto { //update request
    private String ottPlatformname;

    private BigDecimal ottPricepoints;

    private BigDecimal ottQualitypoints;

    private BigDecimal ottTranslationpoints;

    private BigDecimal ottServicepoints;

    private BigDecimal ottTotalpoints;

    private String ottContent;

    private LocalDateTime ottModdate;

    @Builder
    public ReviewOttUpdateDto(String ottPlatformname, BigDecimal ottPricepoints, BigDecimal ottQualitypoints,
                              BigDecimal ottTranslationpoints, BigDecimal ottServicepoints, BigDecimal ottTotalpoints,
                              String ottContent, LocalDateTime ottModdate) {
        this.ottPlatformname = ottPlatformname;
        this.ottPricepoints = ottPricepoints;
        this.ottQualitypoints = ottQualitypoints;
        this.ottTranslationpoints = ottTranslationpoints;
        this.ottServicepoints = ottServicepoints;
        this.ottTotalpoints = ottTotalpoints;
        this.ottContent = ottContent;
        this.ottModdate = LocalDateTime.now();
    }

    public ReviewOttUpdateDto(ReviewOttEntity reviewOttEntity) {
        this.ottPlatformname = reviewOttEntity.getOttPlatformname();
        this.ottPricepoints = reviewOttEntity.getOttPricepoints();
        this.ottQualitypoints = reviewOttEntity.getOttQualitypoints();
        this.ottTranslationpoints = reviewOttEntity.getOttTranslationpoints();
        this.ottServicepoints = reviewOttEntity.getOttServicepoints();
        this.ottTotalpoints = reviewOttEntity.getOttTotalpoints();
        this.ottContent = reviewOttEntity.getOttContent();
        this.ottModdate = reviewOttEntity.getOttModdate();
    }

    public static ReviewOttUpdateDto ofEntity(ReviewOttEntity entity) {
        return new ReviewOttUpdateDto(entity);
    }
}
