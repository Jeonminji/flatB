package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.OttEntity;
import com.example.flatB.domain.entity.RecruitmentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecruitmentSaveDto { //저장
    private String title;
    private String content;
    private String platformname;
    private OttEntity ottEntity;
    private int totalcount;
    private int currentcount;
    private String usedateStart;
    private String usedateEnd;
    private String contact;
    private Member member;
    private LocalDateTime regdate;

    public RecruitmentEntity toEntity() {
        RecruitmentEntity recruitmentEntity = RecruitmentEntity.builder()
                .title(title)
                .content(content)
                .ottEntity(ottEntity)
                .totalcount(totalcount)
                .currentcount(currentcount)
                .usedateStart(usedateStart)
                .usedateEnd(usedateEnd)
                .contact(contact)
                .member(member)
                .regdate(LocalDateTime.now())
                .build();
        return recruitmentEntity;
    }
}
