package com.example.flatB.controller;

import com.example.flatB.domain.dto.UserDto;
import com.example.flatB.common.DefaultRes;
import com.example.flatB.common.ResponseMessage;
import com.example.flatB.common.StatusCode;
import com.example.flatB.service.UserService;
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
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("signup")
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
    @GetMapping("signup/userId/{userId}/exists")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String userId) {
        return ResponseEntity.ok(userService.checkUserIdDuplicate(userId));
    }

    //회원가입 시 닉네임 중복 확인
    @GetMapping("signup/nickname/{nickname}/exists")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.checkNicknameDuplicate(nickname));
    }
}