package com.example.flatB.security;

import com.example.flatB.domain.dto.UserDto;
import com.example.flatB.domain.entity.UserEntity;
import com.example.flatB.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpSession session;

    //userId가 DB에 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserId(userId).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + userId));

        session.setAttribute("user", new UserDto.Response(user));

        //시큐리티 세션에 유저 정보 저장
        return new CustomUserDetails(user);
    }
}
