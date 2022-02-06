package com.ssafy.newbit.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
import com.ssafy.newbit.jwt.CustomAuthenticationProcessingFilter;
import com.ssafy.newbit.jwt.JwtAuthenticationFilter;
import com.ssafy.newbit.jwt.JwtProvider;
import com.ssafy.newbit.jwt.JwtInterceptor;*/
//import com.ssafy.newbit.jwt.CustomAuthenticationProcessingFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor		//이거 내가 추가
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		// for 비밀번호 hashing
                .cors().disable()      // cors 비활성화
                .csrf().disable()      // csrf 비활성화
                .formLogin().disable() //기본 로그인 페이지 없애기
                .headers().frameOptions().disable();

    }
}
