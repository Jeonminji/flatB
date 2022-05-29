package com.example.flatB.service;

import com.example.flatB.domain.dto.RecruitmentSaveDto;
import com.example.flatB.domain.dto.RecruitmentUpdateDto;
import com.example.flatB.domain.entity.RecruitmentEntity;
import com.example.flatB.domain.entity.UserEntity;
import com.example.flatB.repository.RecruitmentRepository;
import com.example.flatB.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentService {
    private final UserRepository userRepository;
    private final RecruitmentRepository recruitmentRepository;

    //글 작성
    @Transactional
    public String post(RecruitmentSaveDto recruitmentSaveDto, String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalAccessError(userId + ": 해당 사용자가 존재하지 않습니다."));
        recruitmentSaveDto.setUserEntity(userEntity);
        recruitmentRepository.save(recruitmentSaveDto.toEntity());
        return "SUCCESS";
    }

    //전체 글 조회(플랫폼별)
    @Transactional
    public List<RecruitmentEntity> getRecruitmentBoards(String ott_platformname) {
        List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAllByPlatformnameOrderByRegdateDesc(ott_platformname);
        return recruitmentEntities;
    }

    //내 글 보기
    @Transactional
    public List<RecruitmentEntity> getRecruitmentBoardByUser(UserEntity userEntity) {
        List<RecruitmentEntity> recruitmentEntities = recruitmentRepository.findAllByUserEntityOrderByBoardNoDesc(userEntity);
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

    //수정, 삭제를 위한 특정 리뷰 조회
    @Transactional
    public RecruitmentEntity getRecruitmentBoard(Long boardNo, String userId) {
        RecruitmentEntity recruitmentEntity = recruitmentRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new IllegalAccessError(boardNo + ": 해당 사용자가 존재하지 않습니다."));
        return recruitmentEntity;
    }

    //글 수정
    @Transactional
    public String update(Long boardNo, RecruitmentUpdateDto recruitmentUpdateDto, String userId) {
        RecruitmentEntity recruitmentEntity = recruitmentRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new IllegalAccessError(boardNo + ": 해당 게시글이 존재하지 않습니다."));

        recruitmentEntity.update(recruitmentUpdateDto.getTitle(), recruitmentUpdateDto.getContent(),
                recruitmentUpdateDto.getPlatformname(), recruitmentUpdateDto.getTotalcount(),
                recruitmentUpdateDto.getCurrentcount(), recruitmentUpdateDto.getUsedateStart(),
                recruitmentUpdateDto.getUsedateEnd(), recruitmentUpdateDto.getContact());

        return "SUCCESS";
    }

    //글 삭제
    public String delete(Long boardNo, String userId) {
        RecruitmentEntity recruitmentEntity = recruitmentRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new IllegalAccessError(boardNo + ": 해당 게시글이 존재하지 않습니다."));
        recruitmentRepository.delete(recruitmentEntity);

        return "SUCCESS";
    }
}