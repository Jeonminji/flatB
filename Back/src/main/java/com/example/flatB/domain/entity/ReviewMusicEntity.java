package com.example.flatB.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "reviewmusic")
public class ReviewMusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicBoardno;

    private String musicPlatformname;

    private BigDecimal musicPricepoints;

    private BigDecimal musicSoundpoints;

    private BigDecimal musicServicepoints;

    private BigDecimal musicTotalpoints;

    private String musicContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_writerid")
    private Member member;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime musicRegdate;

    @UpdateTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime musicModdate;

    @OneToMany(mappedBy = "reviewMusicEntity", cascade = CascadeType.ALL)
    private Set<MusicRecEntity> recommendations = new HashSet<>();

    @Builder
    public ReviewMusicEntity(Long musicBoardno, String musicPlatformname, BigDecimal musicPricepoints,
                             BigDecimal musicSoundpoints, BigDecimal musicServicepoints, BigDecimal musicTotalpoints,
                             String musicContent, Member member, LocalDateTime musicRegdate) {
        this.musicBoardno = musicBoardno;
        this.musicPlatformname = musicPlatformname;
        this.musicPricepoints = musicPricepoints;
        this.musicSoundpoints = musicSoundpoints;
        this.musicServicepoints = musicServicepoints;
        this.musicTotalpoints = musicTotalpoints;
        this.musicContent = musicContent;
        this.member = member;
        this.musicRegdate = LocalDateTime.now();
    }

    public void update(String musicPlatformname, BigDecimal musicPricepoints,
                       BigDecimal musicSoundpoints, BigDecimal musicServicepoints, BigDecimal musicTotalpoints,
                       String musicContent, LocalDateTime musicModdate) {
        this.musicPlatformname = musicPlatformname;
        this.musicPricepoints = musicPricepoints;
        this.musicSoundpoints = musicSoundpoints;
        this.musicServicepoints = musicServicepoints;
        this.musicTotalpoints = musicTotalpoints;
        this.musicContent = musicContent;
        this.musicModdate = LocalDateTime.now();
    }
}
