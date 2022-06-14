package com.example.flatB.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "reviewott")
public class ReviewOttEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ottBoardno;

    private String ottPlatformname;

    private BigDecimal ottPricepoints;

    private BigDecimal ottQualitypoints;

    private BigDecimal ottTranslationpoints;

    private BigDecimal ottServicepoints;

    private BigDecimal ottTotalpoints;

    private String ottContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ott_writerid")
    private UserEntity userEntity;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime ottRegdate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime ottModdate;

    @OneToMany(mappedBy = "reviewOttEntity", cascade = CascadeType.ALL)
    private Set<OttRecEntity> recommendations = new HashSet<>();

    @Builder
    public ReviewOttEntity(Long ottBoardno, String ottPlatformname, BigDecimal ottPricepoints, BigDecimal ottQualitypoints,
                           BigDecimal ottTranslationpoints, BigDecimal ottServicepoints, BigDecimal ottTotalpoints,
                           String ottContent, UserEntity userEntity, LocalDateTime ottRegdate) {
        this.ottBoardno = ottBoardno;
        this.ottPlatformname = ottPlatformname;
        this.ottPricepoints = ottPricepoints;
        this.ottQualitypoints = ottQualitypoints;
        this.ottTranslationpoints = ottTranslationpoints;
        this.ottServicepoints = ottServicepoints;
        this.ottTotalpoints = ottTotalpoints;
        this.ottContent = ottContent;
        this.userEntity = userEntity;
        this.ottRegdate = LocalDateTime.now();
    }

    public void update(String ott_platformname, BigDecimal ott_pricepoints, BigDecimal ott_qualitypoints,
                       BigDecimal ott_translationpoints, BigDecimal ott_servicepoints, BigDecimal ott_totalpoints,
                       String ott_content, LocalDateTime ott_moddate) {
        this.ottPlatformname = ott_platformname;
        this.ottPricepoints = ott_pricepoints;
        this.ottQualitypoints = ott_qualitypoints;
        this.ottTranslationpoints = ott_translationpoints;
        this.ottServicepoints = ott_servicepoints;
        this.ottTotalpoints = ott_totalpoints;
        this.ottContent = ott_content;
        this.ottModdate = LocalDateTime.now();
    }
}
