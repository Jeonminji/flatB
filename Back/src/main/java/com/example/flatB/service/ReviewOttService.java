package com.example.flatB.service;

import com.example.flatB.domain.dto.ReviewOttSaveDto;
import com.example.flatB.domain.dto.ReviewOttUpdateDto;
import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.ReviewOttEntity;
import com.example.flatB.repository.MemberRepository;
import com.example.flatB.repository.ReviewOttRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewOttService {
    private final ReviewOttRepository reviewOttRepository;
    private final MemberRepository memberRepository;

    //리뷰 작성
    @Transactional
    public String post(ReviewOttSaveDto reviewOttSaveDto, String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalAccessError(userId + ": 해당 사용자가 존재하지 않습니다."));
        reviewOttSaveDto.setMember(member);
        reviewOttRepository.save(reviewOttSaveDto.toEntity());
        return "SUCCESS";
    }

    //플랫폼별 모든 리뷰 최신순 조회
    @Transactional
    public List<ReviewOttEntity> getBoards(String ott_platformname) {
        List<ReviewOttEntity> entities = reviewOttRepository.findAllByOttPlatformnameOrderByOttRegdateDesc(
                ott_platformname);
        return entities;
    }

    //플랫폼별 모든 리뷰 별점순 조회
    @Transactional
    public List<ReviewOttEntity> getBoardsByTotalPointsOrder(String ott_platformname) {
        List<ReviewOttEntity> entities = reviewOttRepository.findAllByOttPlatformnameOrderByOttTotalpointsDesc(
                ott_platformname);
        return entities;
    }

    //내가 쓴 모든 리뷰 조회
    @Transactional
    public List<ReviewOttEntity> getBoardsByUser(Member member) {
        List<ReviewOttEntity> entities = reviewOttRepository.findAllByMemberOrderByOttBoardnoDesc(member);
        return entities;
    }

    //수정, 삭제를 위한 특정 리뷰 조회
    @Transactional
    public ReviewOttEntity getBoard(Long board_no) {
        ReviewOttEntity reviewOttEntity = reviewOttRepository.findByOttBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));
        return reviewOttEntity;
    }

    //리뷰 수정
    @Transactional
    public String update(Long board_no, ReviewOttUpdateDto reviewOttUpdateDto) {
        ReviewOttEntity reviewOttEntity = reviewOttRepository.findByOttBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));

        reviewOttEntity.update(reviewOttUpdateDto.getOttPlatformname(),
                reviewOttUpdateDto.getOttPricepoints(), reviewOttUpdateDto.getOttQualitypoints(),
                reviewOttUpdateDto.getOttTranslationpoints(), reviewOttUpdateDto.getOttServicepoints(),
                reviewOttUpdateDto.getOttTotalpoints(), reviewOttUpdateDto.getOttContent(),
                reviewOttUpdateDto.getOttModdate());

        return "SUCCESS";
    }

    //리뷰 삭제
    @Transactional
    public String delete(Long board_no) {
        ReviewOttEntity reviewOttEntity = reviewOttRepository.findByOttBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));
        reviewOttRepository.delete(reviewOttEntity);
        return "SUCCESS";
    }

}
