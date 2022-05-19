package com.example.flatB.controller;

import com.example.flatB.domain.dto.ReportDto;
import com.example.flatB.domain.dto.UserDto;
import com.example.flatB.security.LoginUser;
import com.example.flatB.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ReportController {
    private final ReportService reportService;

    //신고 페이지 보여줌
    @GetMapping("/report")
    public String dispReport() { return "/report"; }

    //신고 게시글 생성
    @PostMapping("/report")
    public ResponseEntity save(@ModelAttribute ReportDto.Request dto, @LoginUser UserDto.Response user) {
        return ResponseEntity.ok(reportService.save(dto, user.getUserId()));
    }
//
//    @GetMapping("/report/write")
//    public String write(@LoginUser UserDto.Response user, Model model) {
//        if (user != null) {
//            model.addAttribute("user", user);
//        }
//        return "/report";
//    }
}
