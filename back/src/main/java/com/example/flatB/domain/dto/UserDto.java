package com.example.flatB.domain.dto;

import com.example.flatB.domain.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
                .build();
        return userEntity;
    }

}
