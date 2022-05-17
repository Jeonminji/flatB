package com.example.flatB.controller;

import com.example.flatB.domain.dto.UserDto;
import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class UserController {
  private final UserService userService;

    // 메인페이지
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    // 회원가입 페이지
//    @GetMapping("/user/signup")
//    public String dispSignup() {
//        return "/signup";
//    }

    // 회원가입 처리
//    @PostMapping("/user/signup")
//    public String execSignup(MemberDto memberDto) {
//        memberService.joinUser(memberDto);
//
//        return "redirect:/user/login";
//    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/login";
    }

    // 로그인 결과 페이지
//    @GetMapping("/user/login/result")
//    public String dispLoginResult() {
//        return "/loginSuccess";
//    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }
  
   //회원가입
    @PostMapping("/user/signup")
    public ResponseEntity joinUser(@RequestBody @Valid UserDto userDto, Errors errors) {
        if(errors.hasErrors()) { //유효성 검사
            Map<String, String> error = new HashMap<>();
            for(FieldError value : errors.getFieldErrors()) {
                error.put(value.getField(), value.getDefaultMessage());
            }
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
        log.info("userId = {}, password = {}", userDto.getUserId(), userDto.getPassword());
        if(userService.joinUser(userDto).equals("Success")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, userDto),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //회원가입 시 아이디 중복 확인
    @GetMapping("/user/signup/userId/{userId}/exists")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(userService.checkUserIdDuplicate(userId));
    }

    //회원가입 시 닉네임 중복 확인
    @GetMapping("/user/signup/nickname/{nickname}/exists")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.checkNicknameDuplicate(nickname));
    }
}