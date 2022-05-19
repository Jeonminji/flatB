package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.ReportEntity;
import com.example.flatB.domain.entity.UserEntity;
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
    private UserEntity user;
    private LocalDateTime date;


    public ReportEntity toEntity() {
        ReportEntity reportEntity = ReportEntity.builder()
                .nickname(nickname)
                .note(note)
                .type(type)
                .content(content)
                .user(user)
                .date(LocalDateTime.now())
                .build();

        return reportEntity;
    }
}