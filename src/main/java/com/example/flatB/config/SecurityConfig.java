package com.example.flatB.config;

import com.example.flatB.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  
    @Override
    public void configure(WebSecurity web) throws Exception {
        //static 디렉토리의 하위 파일 목록은 인증 무시 (= 항상통과)
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().ignoringAntMatchers("/**") //해당 경로 csrf 보호 대상에서 제외
                .and()

                // 페이지 권한 설정
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") //관리자만 접근 가능
                .antMatchers("/user/myinfo", "/report").hasRole("MEMBER") //유저만 접근 가능
                .antMatchers("/**").permitAll() //누구나 접근 가능

                .and() // 로그인 설정
                .formLogin()
                .loginPage("/user/login")
//                .loginProcessingUrl("/user/loginProc") //Security에서 해당 주소로 오는 요청을 낚아채서 수행
//                .failureHandler(customFailureHandler)
                .defaultSuccessUrl("/")
                .permitAll()

                .and() // 로그아웃 설정
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true) //세션 제거

                .and()
                // 403 예외처리 핸들링
                .exceptionHandling().accessDeniedPage("/user/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
}
