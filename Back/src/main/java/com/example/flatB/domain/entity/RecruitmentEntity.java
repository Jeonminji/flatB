package com.example.flatB.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "recruitment")
public class RecruitmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platformname")
    private OttEntity ottEntity;

    @Column(nullable = false)
    private int totalcount;

    @Column(nullable = false)
    private int currentcount;

    @Column(length = 20, nullable = false)
    private String usedateStart;

    @Column(length = 20, nullable = false)
    private String usedateEnd;

    @Column(length = 100, nullable = false)
    private String contact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private UserEntity userEntity;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime regdate;

    public void update(String title, String content, OttEntity ottEntity, int totalcount, int currentcount,
                       String usedateStart, String usedateEnd, String contact) {
        this.title = title;
        this.content = content;
        this.ottEntity = ottEntity;
        this.totalcount = totalcount;
        this.currentcount = currentcount;
        this.usedateStart = usedateStart;
        this.usedateEnd = usedateEnd;
        this.contact = contact;
    }
}
