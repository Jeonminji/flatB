package com.example.flatB.service;

import com.example.flatB.domain.entity.UserEntity;
import com.example.flatB.domain.dto.UserDto;
import com.example.flatB.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    //회원가입
   @Transactional
    public String joinUser(UserDto userDto) {
        //비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        if(checkUserIdDuplicate(userDto.getUserId())) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        if(checkNicknameDuplicate(userDto.getNickname())) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }

        userRepository.save(userDto.toEntity());

        return "Success";
    }

    public boolean checkUserIdDuplicate(String userId) { //아이디 중복 확인
       return userRepository.existsByUserId(userId);
    }

    public boolean checkNicknameDuplicate(String nickname) { //닉네임 중복 확인
       return userRepository.existsByNickname(nickname);
    }

    @Override
    public UserEntity loadUserByUsername(String userId) throws UsernameNotFoundException {
        /* Optional<UserEntity> userEntityWrapper = userRepository.findByUserId(userId);
        UserEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new User(userEntity.getUserId(), userEntity.getPassword(), authorities);*/

        return userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException(userId));
    }

}
