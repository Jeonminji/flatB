package com.example.flatB.service;

import com.example.flatB.domain.dto.ReviewMusicSaveDto;
import com.example.flatB.domain.dto.ReviewMusicUpdateDto;
import com.example.flatB.domain.entity.ReviewMusicEntity;
import com.example.flatB.domain.entity.UserEntity;
import com.example.flatB.repository.ReviewMusicRepository;
import com.example.flatB.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewMusicService {
    private final ReviewMusicRepository reviewMusicRepository;
    private final UserRepository userRepository;

    //리뷰작성
    @Transactional
    public String post(ReviewMusicSaveDto reviewMusicSaveDto, String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalAccessError(userId + ": 해당 사용자가 존재하지 않습니다."));
        reviewMusicSaveDto.setUserEntity(userEntity);
        reviewMusicRepository.save(reviewMusicSaveDto.toEntity());
        return "SUCCESS";
    }

    //플랫폼별 모든 리뷰 최신순 조회
    @Transactional
    public List<ReviewMusicEntity> getBoards(String music_platformname) {
        List<ReviewMusicEntity> entities = reviewMusicRepository.findAllByMusicPlatformnameOrderByMusicRegdateDesc(
                music_platformname);
        return entities;
    }

    //플랫폼별 모든 리뷰 별점순 조회
    @Transactional
    public List<ReviewMusicEntity> getBoardsByTotalPointsOrder(String music_platformname) {
        List<ReviewMusicEntity> entities = reviewMusicRepository.findAllByMusicPlatformnameOrderByMusicTotalpointsDesc(
                music_platformname);
        return entities;
    }

    //내가 쓴 모든 리뷰 조회
    @Transactional
    public List<ReviewMusicEntity> getBoardsByUser(UserEntity userEntity) {
        List<ReviewMusicEntity> entities = reviewMusicRepository.findAllByUserEntityOrderByMusicBoardnoDesc(
                userEntity);
        return entities;
    }

    //수정, 삭제를 위한 특정 리뷰 조회
    @Transactional
    public ReviewMusicEntity getBoard(Long board_no) {
        ReviewMusicEntity reviewMusicEntity = reviewMusicRepository.findByMusicBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));
        return reviewMusicEntity;
    }

    //리뷰 수정
    @Transactional
    public String update(Long board_no, ReviewMusicUpdateDto reviewMusicUpdateDto) {
        ReviewMusicEntity reviewMusicEntity = reviewMusicRepository.findByMusicBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));

        reviewMusicEntity.update(reviewMusicUpdateDto.getMusicPlatformname(), reviewMusicUpdateDto.getMusicPricepoints(),
                reviewMusicUpdateDto.getMusicSoundpoints(), reviewMusicUpdateDto.getMusicServicepoints(),
                reviewMusicUpdateDto.getMusicTotalpoints(), reviewMusicUpdateDto.getMusicContent(),
                reviewMusicUpdateDto.getMusicModdate());

        return "SUCCESS";
    }

    //리뷰 삭제
    @Transactional
    public String delete(Long board_no) {
        ReviewMusicEntity reviewMusicEntity = reviewMusicRepository.findByMusicBoardno(board_no)
                .orElseThrow(() -> new IllegalAccessError(board_no + ": 해당 게시글이 존재하지 않습니다."));

        reviewMusicRepository.delete(reviewMusicEntity);

        return "SUCCESS";
    }
}
