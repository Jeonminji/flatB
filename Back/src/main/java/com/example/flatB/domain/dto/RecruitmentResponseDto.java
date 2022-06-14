package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.RecruitmentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RecruitmentResponseDto { //보여주는거
    private Long boardNo;
    private String title;
    private String content;
    private String platformname;
    private String ottLogo;
    private int totalcount;
    private int currentcount;
    private String usedateStart;
    private String usedateEnd;
    private String contact;
    private String nickname; //작성자 보여주는건 아이디말고 닉네임
    private LocalDateTime regdate;

    public RecruitmentResponseDto(RecruitmentEntity recruitmentEntity) {
        this.boardNo = recruitmentEntity.getBoardNo();
        this.title = recruitmentEntity.getTitle();
        this.content = recruitmentEntity.getContent();
        this.platformname = recruitmentEntity.getOttEntity().getOttName();
        this.ottLogo = recruitmentEntity.getOttEntity().getOttLogo();
        this.totalcount = recruitmentEntity.getTotalcount();
        this.currentcount = recruitmentEntity.getCurrentcount();
        this.usedateStart = recruitmentEntity.getUsedateStart();
        this.usedateEnd = recruitmentEntity.getUsedateEnd();
        this.contact = recruitmentEntity.getContact();
        this.nickname = recruitmentEntity.getUserEntity().getNickname();
        this.regdate = recruitmentEntity.getRegdate();
    }

    public static RecruitmentResponseDto ofEntity(RecruitmentEntity recruitmentEntity) {
        return new RecruitmentResponseDto(recruitmentEntity);
    }

    public static List<RecruitmentResponseDto> ofEntities(List<RecruitmentEntity> recruitmentEntities) {
        return recruitmentEntities.stream().map(RecruitmentResponseDto::ofEntity).collect(Collectors.toList());
    }
}
