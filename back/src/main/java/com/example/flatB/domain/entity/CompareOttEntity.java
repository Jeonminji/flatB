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
@Table(name = "compare_ott")
public class CompareOttEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ottName;

    private String ottPlan;

    private String ottPrice;

    private String ottEtc;

    private String ottPlaybacknum;

    private String ottQuality;

    @Builder
    public CompareOttEntity(Long id, String ottName, String ottPlan, String ottPrice, String ottEtc,
                            String ottPlaybacknum, String ottQuality) {
        this.id = id;
        this.ottName = ottName;
        this.ottPlan = ottPlan;
        this.ottPrice = ottPrice;
        this.ottEtc = ottEtc;
        this.ottPlaybacknum = ottPlaybacknum;
        this. ottQuality = ottQuality;
    }
}
