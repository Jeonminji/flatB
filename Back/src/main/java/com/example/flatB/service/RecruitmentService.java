package com.example.flatB.service;

import com.example.flatB.domain.dto.RecruitmentSaveDto;
import com.example.flatB.domain.dto.RecruitmentUpdateDto;
import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.OttEntity;
import com.example.flatB.domain.entity.RecruitmentEntity;
import com.example.flatB.repository.MemberRepository;
import com.example.flatB.repository.OttRepository;
import com.example.flatB.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final MemberRepository memberRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final OttRepository ottRepository;

    //글 작성
    @Transactional
    public String post(RecruitmentSaveDto recruitmentSaveDto, String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalAccessError(userId + ": 해당 사용자가 존재하지 않습니다."));

        OttEntity ottEntity = ottRepository.findByOttName(recruitmentSaveDto.getPlatformname())
                .orElseThrow(() -> new IllegalAccessError("해당 플랫폼이 존재하지 않습니다."));

        recruitmentSaveDto.setMember(member);
        recruitmentSaveDto.setOttEntity(ottEntity);
        recruitmentRepository.save(recruitmentSaveDto.toEntity());
        return "SUCCESS";
    }

    //인원모집 게시판 전체 글 조회
    @Transactional
    public List<RecruitmentEntity> getRecruitmentBoardsAll(){
        List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAll();
        return recruitmentEntities;
    }

    @Transactional
    public List<RecruitmentEntity> getRecruitingAllBoards(){
        List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAllByTotalcountEqualsCurrentcount();
        return recruitmentEntities;
    }

    //전체 글 조회(플랫폼별)
    @Transactional
    public List<RecruitmentEntity> getRecruitmentBoards(String ott_platformname) {
        OttEntity ottEntity = ottRepository.findByOttName(ott_platformname)
                .orElseThrow(() -> new IllegalAccessError(ott_platformname + ": 해당 플랫폼이 존재하지 않습니다."));
        List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAllByOttEntityOrderByRegdateDesc(ottEntity);
        return recruitmentEntities;
    }

    //내 글 보기
    @Transactional
    public List<RecruitmentEntity> getRecruitmentBoardByUser(Member member) {
        List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAllByMemberOrderByBoardNoDesc(member);
        return recruitmentEntities;
    }

    //글 상세조회
    @Transactional
    public RecruitmentEntity getRecruitmentDetailBoard(Long boardNo) {
        RecruitmentEntity recruitmentEntity = recruitmentRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new IllegalAccessError(boardNo + ": 해당 게시글이 존재하지 않습니다."));
        return recruitmentEntity;
    }

    //모집중인 글 보기
    @Transactional
    public List<RecruitmentEntity> getRecruitingBoard(String ott_platformname) {
        OttEntity ottEntity = ottRepository.findByOttName(ott_platformname)
                .orElseThrow(() -> new IllegalAccessError(ott_platformname + ": 해당 플랫폼이 존재하지 않습니다."));
        List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAllByOttEntityAndTotalcountEqualsCurrentcount(
                ottEntity);
        return recruitmentEntities;
    }

    //수정, 삭제를 위한 특정 리뷰 조회
    @Transactional
    public RecruitmentEntity getRecruitmentBoard(Long boardNo) {
        RecruitmentEntity recruitmentEntity = recruitmentRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new IllegalAccessError(boardNo + ": 해당 게시글이 존재하지 않습니다."));
        return recruitmentEntity;
    }

    //글 수정
    @Transactional
    public String update(Long boardNo, RecruitmentUpdateDto recruitmentUpdateDto) {
        RecruitmentEntity recruitmentEntity = recruitmentRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new IllegalAccessError(boardNo + ": 해당 게시글이 존재하지 않습니다."));

        OttEntity ottEntity = ottRepository.findByOttName(recruitmentUpdateDto.getPlatformname())
                .orElseThrow(() -> new IllegalAccessError( "해당 플랫폼이 존재하지 않습니다."));

        recruitmentEntity.update(recruitmentUpdateDto.getTitle(), recruitmentUpdateDto.getContent(),
                ottEntity, recruitmentUpdateDto.getTotalcount(),
                recruitmentUpdateDto.getCurrentcount(), recruitmentUpdateDto.getUsedateStart(),
                recruitmentUpdateDto.getUsedateEnd(), recruitmentUpdateDto.getContact());

        return "SUCCESS";
    }

    //글 삭제
    public String delete(Long boardNo) {
        RecruitmentEntity recruitmentEntity = recruitmentRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new IllegalAccessError(boardNo + ": 해당 게시글이 존재하지 않습니다."));
        recruitmentRepository.delete(recruitmentEntity);

        return "SUCCESS";
    }
}