package com.example.flatB.service;

import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.OttRecEntity;
import com.example.flatB.domain.entity.ReviewOttEntity;
import com.example.flatB.repository.MemberRepository;
import com.example.flatB.repository.OttRecRepository;
import com.example.flatB.repository.ReviewOttRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OttRecService {
    private final OttRecRepository ottRecRepository;
    private final ReviewOttRepository reviewOttRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public boolean addRec(String user_id, Long board_no) { //추천 추가
        Member member = memberRepository.findByUserId(user_id)
                .orElseThrow(() -> new IllegalAccessError(user_id + ": 해당 사용자가 존재하지 않습니다."));

        ReviewOttEntity reviewOttEntity = reviewOttRepository.findByOttBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));

        //사용자가 이미 추천을 눌렀으면 취소, 아니면 추가
        ottRecRepository.findByMemberAndReviewOttEntity(member, reviewOttEntity)
                .ifPresentOrElse(ottRecEntity -> ottRecRepository.deleteById(ottRecEntity.getId()),
                        () -> ottRecRepository.save(new OttRecEntity(reviewOttEntity, member)));
       return true;
    }

    @Transactional
    public Integer countRec(Long board_no) {
        ReviewOttEntity reviewOttEntity = reviewOttRepository.findByOttBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));
        int count = ottRecRepository.countByReviewOttEntity(reviewOttEntity);
        return count;
    }
}
