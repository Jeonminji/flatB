package com.example.flatB.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@Table(name = "user")
public class Member {
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

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) { this.password = password; }


    @Builder
    public Member(String userId, String password, String name, String nickname,
                  String contact, String age, String gender, LocalDateTime join_date,
                  Authority authority) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.contact = contact;
        this.age = age;
        this.gender = gender;
        this.join_date = join_date;
        this.authority = authority;
    }

//    @Builder
//    public Member(Long id, String email, String password, String nickname, Authority authority) {
//        this.id = id;
//        this.email = email;
//        this.password = password;
//        this.nickname = nickname;
//        this.authority = authority;
//    }
}
