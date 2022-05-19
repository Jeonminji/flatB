package com.example.flatB.domain.dto;

import com.example.flatB.domain.Role;
import com.example.flatB.domain.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "연락처를 입력해주세요.")
    private String contact;

    @NotBlank(message = "태어난 연도를 입력해주세요.")
    private String age;

    @NotBlank(message = "성별을 입력해주세요.")
    private String gender;

    private LocalDateTime join_date;

    private Role role;

    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .nickname(nickname)
                .contact(contact)
                .age(age)
                .gender(gender)
                .join_date(LocalDateTime.now())
                .role(role.MEMBER)
                .build();
        return userEntity;
    }

    //로그인한 사용자 정보 세션에 저장하기 위한 클래스
    @Getter
    public static class Response implements Serializable {

        private String userId;
        private String name;
        private String nickname;
        private String contact;
        private String age;
        private String gender;
        private LocalDateTime join_date;

        // Entity -> Dto
        public Response(UserEntity user) {
            this.userId = user.getUserId();
            this.name = user.getName();
            this.nickname = user.getNickname();
            this.contact = user.getContact();
            this.age = user.getAge();
            this.gender = user.getGender();
            this.join_date = user.getJoin_date();
        }
    }
}
