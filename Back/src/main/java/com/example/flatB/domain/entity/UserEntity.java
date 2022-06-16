package com.example.flatB.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    private String userId;

    private String password;

    private String name;

    private String nickname;

    private String contact;

    private String age;

    private String gender;

    @CreationTimestamp
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm")
    private LocalDateTime join_date;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Builder
    public UserEntity(String userId, String password, String name, String nickname,
                      String contact, String age, String gender, LocalDateTime join_date) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.contact = contact;
        this.age = age;
        this.gender = gender;
        this.join_date = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {  //사용자의 id를 반환
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
