package com.example.flatB.controller;

import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.dto.MemberResponseDto;
import com.example.flatB.domain.dto.ReviewOttResponseDto;
import com.example.flatB.domain.dto.ReviewOttSaveDto;
import com.example.flatB.domain.dto.ReviewOttUpdateDto;
import com.example.flatB.domain.entity.Member;
import com.example.flatB.domain.entity.ReviewOttEntity;
import com.example.flatB.repository.MemberRepository;
import com.example.flatB.service.MemberService;
import com.example.flatB.service.OttRecService;
import com.example.flatB.service.ReviewOttService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/reviewOtt")
@RequiredArgsConstructor
public class ReviewOttController {
    private final ReviewOttService reviewOttService;
    private final OttRecService ottRecService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @PostMapping("/write")
    public ResponseEntity post(@RequestBody ReviewOttSaveDto reviewOttSaveDto) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
            , HttpStatus.NOT_FOUND);
        }

        if (reviewOttService.post(reviewOttSaveDto, member.getUserId()).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.POST_CREATED, reviewOttSaveDto),
                    HttpStatus.CREATED);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_NOT_CREATED),
                HttpStatus.BAD_REQUEST);

    }

    //???????????? ?????? ??????
    @GetMapping("/{platformname}")
    public ResponseEntity getBoards(@PathVariable String platformname,
                                    @RequestParam(required = false) boolean latestOrder,
                                    @RequestParam(required = false) boolean totalPoints,
                                    @RequestParam(required = false) boolean recOrder) {
        String ott_platformname = "";
        switch (platformname) {
            case "netflix":
                ott_platformname = "????????????";
                break;
            case "watcha":
                ott_platformname = "??????";
                break;
            case "disneyplus":
                ott_platformname = "??????????????????";
                break;
            case "wavve":
                ott_platformname = "?????????";
                break;
            case "tving":
                ott_platformname = "??????";
                break;
            case "laftel":
                ott_platformname = "?????????";
                break;
        }

        List<ReviewOttEntity> reviewOttEntities;
        if (latestOrder) { //????????? ??????
            reviewOttEntities = reviewOttService.getBoards(ott_platformname);
        } else if (totalPoints) { //????????? ??????
            reviewOttEntities = reviewOttService.getBoardsByTotalPointsOrder(ott_platformname);
        } else { //default
            reviewOttEntities = reviewOttService.getBoards(ott_platformname);
        }

        List<ReviewOttResponseDto> boardList = ReviewOttResponseDto.ofEntities(reviewOttEntities);

        if (recOrder) { //????????? ??????
            boardList.sort((a, b) -> b.getRecommendations() - a.getRecommendations());
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, boardList), HttpStatus.OK);
    }

    //?????? ??? ?????? ?????? ??????
    @GetMapping("/my")
    public ResponseEntity getBoardByUser() {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        Optional<Member> memberEntity = memberRepository.findByUserId(member.getUserId());
        List<ReviewOttEntity> reviewOttEntities = reviewOttService.getBoardsByUser(memberEntity.get());
        List<ReviewOttResponseDto> boardList = ReviewOttResponseDto.ofEntities(reviewOttEntities);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, boardList), HttpStatus.OK);
    }

    //?????? ?????? ?????? ?????? ??? ????????????
    @GetMapping("/update/{boardNo}")
    public ResponseEntity getBoard(@PathVariable Long boardNo) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }
        ReviewOttEntity reviewOttEntity = reviewOttService.getBoard(boardNo);
        if (checkAuthority(reviewOttEntity, member.getUserId()).equals(ResponseMessage.NOT_FOUND_USER)) { //?????? ??????
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewOttUpdateDto reviewOttUpdateDto = ReviewOttUpdateDto.ofEntity(reviewOttEntity);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, reviewOttUpdateDto),
                HttpStatus.OK);
    }

    //?????? ??????
    @PutMapping("/update/{boardNo}")
    public ResponseEntity updateBoard(@PathVariable Long boardNo, @RequestBody ReviewOttUpdateDto reviewOttUpdateDto) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewOttEntity reviewOttEntity = reviewOttService.getBoard(boardNo);
        if (checkAuthority(reviewOttEntity, member.getUserId()).equals(ResponseMessage.NOT_FOUND_USER)) { //?????? ??????
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        if(reviewOttService.update(boardNo, reviewOttUpdateDto).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.POST_UPDATE, reviewOttUpdateDto),
                    HttpStatus.CREATED);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_UPDATE_FAIL)
                , HttpStatus.BAD_REQUEST);
    }

    //?????? ??????
    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity deleteBoard(@PathVariable Long boardNo) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewOttEntity reviewOttEntity = reviewOttService.getBoard(boardNo);
        if (checkAuthority(reviewOttEntity, member.getUserId()).equals(ResponseMessage.NOT_FOUND_USER)) { //?????? ??????
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        if (reviewOttService.delete(boardNo).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.POST_DELETE),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_DELETE_FAIL)
                , HttpStatus.BAD_REQUEST);
    }

    //?????? ?????????
   @PostMapping("/recommendation/{boardNo}")
    public ResponseEntity addRec(@PathVariable Long boardNo) {
       MemberResponseDto member = memberService.getMyInfoBySecurity();
       System.out.println("?????????!!!!!!!!" + member.getUserId());
       if (member.getUserId() == null) {
           return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                   , HttpStatus.NOT_FOUND);
       }
       boolean result = false;
       if (member.getUserId() != null) {
           result = ottRecService.addRec(member.getUserId(), boardNo);
       }

       if (result) {
           return new ResponseEntity(HttpStatus.OK);
       } else
           return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //?????? ???????????? ????????? ??????
    @GetMapping("/recommendation/{boardNo}")
    public ResponseEntity countRec(@PathVariable Long boardNo) {
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        count = ottRecService.countRec(boardNo);
        map.put("recommendations", count);
        return new ResponseEntity(map, HttpStatus.OK);
    }

    //?????? ??????
    private String checkAuthority(ReviewOttEntity reviewOttEntity, String userId) {
        if(!reviewOttEntity.getMember().getUserId().equals(userId))
            return ResponseMessage.NOT_FOUND_USER;
        return ResponseMessage.READ_USER;
    }

    //??? ?????? ?????? ??????
    @GetMapping("/write/check")
    public ResponseEntity writeCheckAuthority() {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER), HttpStatus.OK);
    }

    //??? ?????? ?????? ??????
    @GetMapping("/update/check/{boardNo}")
    public ResponseEntity updateCheckAuthority(@PathVariable Long boardNo) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        ReviewOttEntity reviewOttEntity = reviewOttService.getBoard(boardNo);

        if (checkAuthority(reviewOttEntity, member.getUserId()).equals(ResponseMessage.NOT_FOUND_USER)) { //?????? ??????
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ), HttpStatus.OK);
    }

}