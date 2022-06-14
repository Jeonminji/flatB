package com.example.flatB;

import com.example.flatB.domain.dto.UserDto;
import com.example.flatB.repository.UserRepository;
import com.example.flatB.service.UserService;
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
	UserRepository userRepository;
	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	String PASSWORD = "password";

	@Test
	public void 회원가입_성공() {
		UserDto userDto = UserDto.builder()
				.userId("test1")
				.password(PASSWORD)
				.name("유인희")
				.nickname("test")
				.age("20")
				.contact("010-0000-0000")
				.gender("여성")
				.build();
		userService.joinUser(userDto);

	}

	@Test
	public void 회원가입_중복아이디() {
		UserDto userDto1 = UserDto.builder()
				.userId("test12") //아이디중복
				.password(PASSWORD)
				.name("유인희")
				.nickname("테스트")
				.age("20")
				.contact("010-0000-0000")
				.gender("여성")
				.build();

		userService.joinUser(userDto1);

		IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () ->
				userService.joinUser(userDto1));
		System.out.println(e.getMessage());
		//Assertions.assertEquals("이미 존재하는 아이디입니다.", e.getMessage());

	}

	@Test
	public void 회원가입_중복닉네임() {
		UserDto userDto1 = UserDto.builder()
				.userId("test3")
				.password(PASSWORD)
				.name("유인희")
				.nickname("닉네임테스트") //닉네임중복
				.age("20")
				.contact("010-0000-0000")
				.gender("여성")
				.build();

		userService.joinUser(userDto1);

		IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () ->
				userService.joinUser(userDto1));
		System.out.println(e.getMessage());
		//Assertions.assertEquals("이미 존재하는 닉네임입니다.", e.getMessage());

	}
}
