package com.example.flatB.controller;

import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.dto.RecruitmentResponseDto;
import com.example.flatB.domain.dto.RecruitmentSaveDto;
import com.example.flatB.domain.dto.RecruitmentUpdateDto;
import com.example.flatB.domain.entity.RecruitmentEntity;
import com.example.flatB.domain.entity.UserEntity;
import com.example.flatB.repository.UserRepository;
import com.example.flatB.service.RecruitmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/recruitmentOtt")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;
    private final UserRepository userRepository;

    //글 작성
    @PostMapping("/write")
    public ResponseEntity post(@RequestBody RecruitmentSaveDto recruitmentSaveDto, Principal principal) {
        if (principal.getName().isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }

        if (recruitmentService.post(recruitmentSaveDto, principal.getName()).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.POST_CREATED,
                    recruitmentSaveDto), HttpStatus.CREATED);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_NOT_CREATED), HttpStatus.BAD_REQUEST);
    }

    //플랫폼별 인원 모집 글 조회
    @GetMapping("/{platformname}")
    public ResponseEntity getRecruitmentBoards(@PageableDefault(size = 8) Pageable pageable,
                                               @PathVariable String platformname,
                                               @RequestParam(required = false) boolean recruiting) {
        String ott_platformname = "";
        switch (platformname) {
            case "netflix":
                ott_platformname = "넷플릭스";
                break;
            case "watcha":
                ott_platformname = "왓챠";
                break;
            case "disneyplus":
                ott_platformname = "디즈니플러스";
                break;
            case "wavve":
                ott_platformname = "웨이브";
                break;
            case "tving":
                ott_platformname = "티빙";
                break;
            case "laftel":
                ott_platformname = "라프텔";
                break;
        }

        List<RecruitmentEntity> recruitmentEntities;
        if (recruiting) //모집중인 글만 보기 (모집중 필터)
            recruitmentEntities =
                    recruitmentService.getRecruitingBoard(ott_platformname);
        else
            recruitmentEntities =
                    recruitmentService.getRecruitmentBoards(ott_platformname);

        List<RecruitmentResponseDto> boardList =
                RecruitmentResponseDto.ofEntities(recruitmentEntities);

        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > boardList.size() ? boardList.size() : (start + pageable.getPageSize());
        Page<RecruitmentResponseDto> responseDtos = new PageImpl<>(boardList.subList(start, end), pageable, boardList.size());

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, responseDtos), HttpStatus.OK);
    }

    //글 상세조회
    @GetMapping("/detail/{boardNo}")
    public ResponseEntity getRecruitmentDetailBoard(@PathVariable Long boardNo) {
        RecruitmentEntity recruitmentEntity = recruitmentService.getRecruitmentDetailBoard(boardNo);

        RecruitmentResponseDto recruitmentResponseDto = RecruitmentResponseDto.ofEntity(recruitmentEntity);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, recruitmentResponseDto), HttpStatus.OK);
    }


    //내 글 필터
    @GetMapping("/my")
    public ResponseEntity getBoardsByUser(@PageableDefault(size = 8) Pageable pageable, Principal principal) {
        if (principal.getName().isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }

        Optional<UserEntity> userEntity = userRepository.findByUserId(principal.getName());
        List<RecruitmentEntity> recruitmentEntities = recruitmentService.getRecruitmentBoardByUser(userEntity.get());
        List<RecruitmentResponseDto> boardList = RecruitmentResponseDto.ofEntities(recruitmentEntities);

        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > boardList.size() ? boardList.size() : (start + pageable.getPageSize());
        Page<RecruitmentResponseDto> responseDtos = new PageImpl<>(boardList.subList(start, end), pageable, boardList.size());

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, responseDtos), HttpStatus.OK);
    }

    //권한 확인
    private String checkAuthority(RecruitmentEntity recruitmentEntity, String userId) {
        if(!recruitmentEntity.getUserEntity().getUserId().equals(userId))
            return ResponseMessage.NOT_FOUND_USER;
        return ResponseMessage.READ_USER;
    }

    //리뷰 수정 위해 원래 값 가져오기
    @GetMapping("/update/{boardNo}")
    public ResponseEntity getRecruitmentBoard(@PathVariable Long boardNo, Principal principal) {
        if (principal.getName().isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }
        RecruitmentEntity recruitmentEntity = recruitmentService.getRecruitmentBoard(boardNo, principal.getName());

        if (checkAuthority(recruitmentEntity, principal.getName()).equals(ResponseMessage.NOT_FOUND_USER)) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }

        RecruitmentUpdateDto recruitmentUpdateDto = RecruitmentUpdateDto.ofEntity(recruitmentEntity);

        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.POST_READ, recruitmentUpdateDto), HttpStatus.OK);
    }


    //리뷰 수정
    @PutMapping("/update/{boardNo}")
    public ResponseEntity updateBoard(@PathVariable Long boardNo, @RequestBody RecruitmentUpdateDto recruitmentUpdateDto,
                                      Principal principal) {
        if (principal.getName().isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        RecruitmentEntity recruitmentEntity = recruitmentService.getRecruitmentBoard(boardNo, principal.getName());

        if (checkAuthority(recruitmentEntity, principal.getName()).equals(ResponseMessage.NOT_FOUND_USER)) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }

        if(recruitmentService.update(boardNo, recruitmentUpdateDto, principal.getName()).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.POST_UPDATE, recruitmentUpdateDto),
                    HttpStatus.CREATED);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_UPDATE_FAIL)
                , HttpStatus.BAD_REQUEST);
    }

    //리뷰 삭제
    @DeleteMapping("/delete/{boardNo}")
    public ResponseEntity deleteBoard(@PathVariable Long boardNo, Principal principal) {
        if (principal.getName().isEmpty()) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        RecruitmentEntity recruitmentEntity = recruitmentService.getRecruitmentBoard(boardNo, principal.getName());

        if (checkAuthority(recruitmentEntity, principal.getName()).equals(ResponseMessage.NOT_FOUND_USER)) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER), HttpStatus.NOT_FOUND);
        }

        if (recruitmentService.delete(boardNo, principal.getName()).equals("SUCCESS")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.POST_DELETE),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.POST_DELETE_FAIL)
                , HttpStatus.BAD_REQUEST);
    }
}
