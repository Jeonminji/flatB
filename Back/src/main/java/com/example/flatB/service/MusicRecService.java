package com.example.flatB.service;

import com.example.flatB.domain.entity.*;
import com.example.flatB.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MusicRecService {
    private final MusicRecRepository musicRecRepository;
    private final ReviewMusicRepository reviewMusicRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public boolean addRec(String user_id, Long board_no) { //추천 추가
        Member member = memberRepository.findByUserId(user_id)
                .orElseThrow(() -> new IllegalAccessError(user_id + ": 해당 사용자가 존재하지 않습니다."));

        ReviewMusicEntity reviewMusicEntity = reviewMusicRepository.findByMusicBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));

        //사용자가 이미 추천을 눌렀으면 취소, 아니면 추가
        musicRecRepository.findByMemberAndReviewMusicEntity(member, reviewMusicEntity)
                .ifPresentOrElse(musicRecEntity -> musicRecRepository.deleteById(musicRecEntity.getId()),
                        () -> musicRecRepository.save(new MusicRecEntity(reviewMusicEntity, member)));
        return true;
    }

    @Transactional
    public Integer countRec(Long board_no) {
        ReviewMusicEntity reviewMusicEntity = reviewMusicRepository.findByMusicBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));
        int count = musicRecRepository.countByReviewMusicEntity(reviewMusicEntity);
        return count;
    }
}
