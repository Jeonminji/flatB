package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.RecruitmentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RecruitmentUpdateDto { //수정
    private String title;
    private String content;
    private String platformname;
    private int totalcount;
    private int currentcount;
    private String usedateStart;
    private String usedateEnd;
    private String contact;

    @Builder
    public RecruitmentUpdateDto(String title, String content, String platformname, int totalcount, int currentcount,
                                String usedateStart, String usedateEnd, String contact) {
        this.title = title;
        this.content = content;
        this.platformname = platformname;
        this.totalcount = totalcount;
        this.currentcount = currentcount;
        this.usedateStart = usedateStart;
        this.usedateEnd = usedateEnd;
        this.contact = contact;
    }

    public RecruitmentUpdateDto(RecruitmentEntity recruitmentEntity) {
        this.title = recruitmentEntity.getTitle();
        this.content = recruitmentEntity.getContent();
        this.platformname = recruitmentEntity.getOttEntity().getOttName();
        this.totalcount = recruitmentEntity.getTotalcount();
        this.currentcount = recruitmentEntity.getCurrentcount();
        this.usedateStart = recruitmentEntity.getUsedateStart();
        this.usedateEnd = recruitmentEntity.getUsedateEnd();
        this.contact = recruitmentEntity.getContact();
    }

    public static RecruitmentUpdateDto ofEntity(RecruitmentEntity recruitmentEntity) {
        return new RecruitmentUpdateDto(recruitmentEntity);
    }
}
