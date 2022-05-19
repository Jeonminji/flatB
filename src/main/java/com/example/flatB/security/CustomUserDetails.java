package com.example.flatB.security;

import com.example.flatB.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티의 고유한 세션 저장소에 저장
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;

    @Override
    public String getPassword() { return userEntity.getPassword(); }

    @Override
    public String getUsername() { return userEntity.getUserId(); }

    //계정 만료 여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //사용자 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

    //유저의 권한 목록
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(() -> "ROLE_"+userEntity.getRole());

        return collectors;
    }
}