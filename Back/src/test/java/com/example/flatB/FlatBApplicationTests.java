package com.example.flatB;

import com.example.flatB.domain.dto.MemberRequestDto;
import com.example.flatB.repository.MemberRepository;
import com.example.flatB.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class FlatBApplicationTests {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	AuthService authService;

	@Autowired
	PasswordEncoder passwordEncoder;

	String PASSWORD = "password";

	@Test
	public void 회원가입_성공() {
		MemberRequestDto memberRequestDto = MemberRequestDto.builder()
				.userId("testsingnup")
				.password(PASSWORD)
				.name("유인희")
				.nickname("회원가입테스트")
				.age("20")
				.contact("010-0000-0000")
				.gender("여성")
				.build();

		Assertions.assertEquals("Success", authService.joinUser(memberRequestDto));

	}

	@Test
	public void 회원가입_중복아이디() {
		MemberRequestDto memberRequestDto1 = MemberRequestDto.builder()
				.userId("test12") //아이디중복
				.password(PASSWORD)
				.name("유인희")
				.nickname("중복아이디테스트")
				.age("20")
				.contact("010-0000-0000")
				.gender("여성")
				.build();
//		authService.joinUser(memberRequestDto1);

		IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () ->
				authService.joinUser(memberRequestDto1));
		Assertions.assertEquals("이미 존재하는 아이디입니다.", e.getMessage());

	}

	@Test
	public void 회원가입_중복닉네임() {
		MemberRequestDto memberRequestDto1 = MemberRequestDto.builder()
				.userId("test33")
				.password(PASSWORD)
				.name("유인희")
				.nickname("닉네임테스트") //닉네임중복
				.age("20")
				.contact("010-0000-0000")
				.gender("여성")
				.build();

//		authService.joinUser(memberRequestDto1);

		IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () ->
				authService.joinUser(memberRequestDto1));
		Assertions.assertEquals("이미 존재하는 닉네임입니다.", e.getMessage());

	}
}
