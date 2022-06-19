package com.example.flatB.controller;

import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.domain.dto.MemberRequestDto;
import com.example.flatB.domain.dto.TokenDto;
import com.example.flatB.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    //로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody @Valid MemberRequestDto memberRequestDto, Errors errors) {
        if(errors.hasErrors()) { //유효성 검사
            Map<String, String> error = new HashMap<>();
            for(FieldError value : errors.getFieldErrors()) {
                error.put(value.getField(), value.getDefaultMessage());
            }
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }
        log.info("userId = {}, password = {}", memberRequestDto.getUserId(), memberRequestDto.getPassword());
        if(authService.joinUser(memberRequestDto).equals("Success")) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, memberRequestDto),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    //로그아웃
    @GetMapping("/logout")
    public ResponseEntity logout() {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGOUT_SUCCESS), HttpStatus.OK);
    }

    //회원가입 시 아이디 중복 확인
    @GetMapping("/signup/userId/{userId}/exists")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(authService.checkUserIdDuplicate(userId));
    }

    //회원가입 시 닉네임 중복 확인
    @GetMapping("/signup/nickname/{nickname}/exists")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname) {
        return ResponseEntity.ok(authService.checkNicknameDuplicate(nickname));
    }
}
