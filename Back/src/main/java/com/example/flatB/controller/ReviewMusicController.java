package com.example.flatB.controller;

import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.dto.*;
import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.ReviewMusicEntity;
import com.example.flatB.repository.MemberRepository;
import com.example.flatB.service.MemberService;
import com.example.flatB.service.MusicRecService;
import com.example.flatB.service.ReviewMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/reviewMusic")
@RequiredArgsConstructor
public class ReviewMusicController {
    private final ReviewMusicService reviewMusicService;
    private final MusicRecService musicRecService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    //리뷰 작성
    @PostMapping("/write")
    public ResponseEntity post(@RequestBody ReviewMusicSaveDto reviewMusicSaveDto) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        if (reviewMusicService.post(reviewMusicSaveDto, member.getUserId()).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.POST_CREATED, reviewMusicSaveDto),
                    HttpStatus.CREATED);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_NOT_CREATED),
                HttpStatus.BAD_REQUEST);
    }

    //플랫폼별 리뷰 조회
    @GetMapping("/{platformname}")
    public ResponseEntity getBoards(@PathVariable String platformname,
                                    @RequestParam(required = false) boolean latestOrder,
                                    @RequestParam(required = false) boolean totalPoints,
                                    @RequestParam(required = false) boolean recOrder) {
        String music_platformname = "";
        switch (platformname) {
            case "melon":
                music_platformname = "멜론";
                break;
            case "genie":
                music_platformname = "지니";
                break;
            case "bugs":
                music_platformname = "벅스";
                break;
            case "youtubemusic":
                music_platformname = "유튜브뮤직";
                break;
            case "flo":
                music_platformname = "플로";
                break;
            case "spotify":
                music_platformname = "스포티파이";
                break;
        }

        List<ReviewMusicEntity> reviewMusicEntities;
        if (latestOrder) { //최신순 조회
            reviewMusicEntities = reviewMusicService.getBoards(music_platformname);
        } else if (totalPoints) { //별점순 조회
            reviewMusicEntities = reviewMusicService.getBoardsByTotalPointsOrder(music_platformname);
        } else { //default
            reviewMusicEntities = reviewMusicService.getBoards(music_platformname);
        }

        List<ReviewMusicResponseDto> boardList = ReviewMusicResponseDto.ofEntities(reviewMusicEntities);

        if(recOrder) { //추천순 조회
            boardList.sort((a, b) -> b.getRecommendations() - a.getRecommendations());
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, boardList), HttpStatus.OK);
    }

    //내가 쓴 모든 리뷰 조회
    @GetMapping("/my")
    public ResponseEntity getBoardByUser() {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        Optional<Member> memberEntity = memberRepository.findByUserId(member.getUserId());
        List<ReviewMusicEntity> reviewMusicEntities = reviewMusicService.getBoardsByUser(memberEntity.get());
        List<ReviewMusicResponseDto> boardList = ReviewMusicResponseDto.ofEntities(reviewMusicEntities);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, boardList), HttpStatus.OK);
    }

    //리뷰 수정 위해 원래 값 가져오기
    @GetMapping("/update/{boardNo}")
    public ResponseEntity getBoard(@PathVariable Long boardNo) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewMusicEntity reviewMusicEntity = reviewMusicService.getBoard(boardNo);

        if (checkAuthority(reviewMusicEntity, member.getUserId()).equals(ResponseMessage.NOT_FOUND_USER)) { //권한 확인
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewMusicUpdateDto reviewMusicUpdateDto = ReviewMusicUpdateDto.ofEntity(reviewMusicEntity);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, reviewMusicUpdateDto),
                HttpStatus.OK);
    }

    //리뷰 수정
    @PutMapping("/update/{boardNo}")
    public ResponseEntity updateBoard(@PathVariable Long boardNo, @RequestBody ReviewMusicUpdateDto reviewMusicUpdateDto) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewMusicEntity reviewMusicEntity = reviewMusicService.getBoard(boardNo);
        if (checkAuthority(reviewMusicEntity, member.getUserId()).equals(ResponseMessage.NOT_FOUND_USER)) { //권한 확인
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        if(reviewMusicService.update(boardNo, reviewMusicUpdateDto).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.POST_UPDATE, reviewMusicUpdateDto),
                    HttpStatus.CREATED);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_UPDATE_FAIL)
                , HttpStatus.BAD_REQUEST);
    }

    //리뷰 삭제
    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity deleteBoard(@PathVariable Long boardNo) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewMusicEntity reviewMusicEntity = reviewMusicService.getBoard(boardNo);
        if (checkAuthority(reviewMusicEntity,member.getUserId()).equals(ResponseMessage.NOT_FOUND_USER)) { //권한 확인
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        if (reviewMusicService.delete(boardNo).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.POST_DELETE),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_DELETE_FAIL)
                , HttpStatus.BAD_REQUEST);

    }

    //추천 누르기
    @PostMapping("/recommendation/{boardNo}")
    public ResponseEntity addRec(@PathVariable Long boardNo) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        boolean result = false;
       if (member.getUserId() != null) {
           result = musicRecService.addRec(member.getUserId(), boardNo);
       }

        if (result) {
            return new ResponseEntity(HttpStatus.OK);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //해당 게시글의 추천수 조회
    @GetMapping("/recommendation/{boardNo}")
    public ResponseEntity countRec(@PathVariable Long boardNo) {
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        count = musicRecService.countRec(boardNo);
        map.put("recommendations", count);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    //권한 확인
    private String checkAuthority(ReviewMusicEntity reviewMusicEntity, String userId) {
        if(!reviewMusicEntity.getMember().getUserId().equals(userId))
            return ResponseMessage.NOT_FOUND_USER;
        return ResponseMessage.READ_USER;
    }

    //글 수정 권한 확인
    @GetMapping("/update/check/{boardNo}")
    public ResponseEntity updateCheckAuthority(@PathVariable Long boardNo) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewMusicEntity reviewMusicEntity = reviewMusicService.getBoard(boardNo);

        if (checkAuthority(reviewMusicEntity, member.getUserId()).equals(ResponseMessage.NOT_FOUND_USER)) { //권한 확인
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ), HttpStatus.OK);
    }
}
