package com.example.flatB.service;

import com.example.flatB.config.TokenProvider;
import com.example.flatB.domain.dto.MemberRequestDto;
import com.example.flatB.domain.dto.TokenDto;
import com.example.flatB.domain.entity.Member;
import com.example.flatB.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    //로그인
    public TokenDto login(MemberRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);

        return tokenProvider.generateTokenDto(authentication);
    }

    //회원가입
    @Transactional
    public String joinUser(MemberRequestDto requestDto) {
        if(checkUserIdDuplicate(requestDto.getUserId())) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        if(checkNicknameDuplicate(requestDto.getNickname())) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }

        Member member = requestDto.toMember(passwordEncoder);
        memberRepository.save(member);

        return "Success";
    }

    public boolean checkUserIdDuplicate(String userId) { //아이디 중복 확인
        return memberRepository.existsByUserId(userId);
    }

    public boolean checkNicknameDuplicate(String nickname) { //닉네임 중복 확인
        return memberRepository.existsByNickname(nickname);
    }

}