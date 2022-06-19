package com.example.flatB.controller;

import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.dto.MemberResponseDto;
import com.example.flatB.domain.dto.ReportDto;
import com.example.flatB.service.MemberService;
import com.example.flatB.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final MemberService memberService;

//    //신고 페이지 보여줌
//    @GetMapping("/report")
//    public String dispReport() { return "/report"; }

    //신고 게시글 생성
    @PostMapping("/report")
    public ResponseEntity post(@RequestBody ReportDto reportDto) {
        MemberResponseDto member = memberService.getMyInfoBySecurity();
        if (member.getUserId() == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.NOT_FOUND, ResponseMessage.NOT_FOUND_USER)
                    , HttpStatus.NOT_FOUND);
        }

        reportService.post(reportDto,member.getUserId());

        return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.POST_CREATED, reportDto)
                , HttpStatus.CREATED);
    }
}
