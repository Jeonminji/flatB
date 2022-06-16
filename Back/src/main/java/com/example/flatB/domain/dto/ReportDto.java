package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.ReportEntity;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportDto {

    private String nickname;
    private String note;
    private String type;
    private String content;
    private Member member;
    private LocalDateTime date;


    public ReportEntity toEntity() {
        ReportEntity reportEntity = ReportEntity.builder()
                .nickname(nickname)
                .note(note)
                .type(type)
                .content(content)
                .member(member)
                .date(LocalDateTime.now())
                .build();

        return reportEntity;
    }
}