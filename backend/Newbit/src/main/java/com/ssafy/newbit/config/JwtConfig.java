package com.ssafy.newbit.config;

import com.ssafy.newbit.jwt.JwtInterceptor;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
@RequiredArgsConstructor
public class JwtConfig extends WebMvcConfigurationSupport {
	@Autowired
	private JwtInterceptor jwtInterceptor;  //final
	
	
    private static final String[] INCLUDE_PATHS = {
    		// 인증 필요한 경로 지정 (인증 O)
            "/user/setting**","/user/setting/**",
            "/user/jwttest/**"
            
    };
	
    private static final String[] EXCLUDE_PATHS = {
    		// 제외할 경로 지정  (인증 X)
            //"/**","",
            "/user/signup**","/user/signup/**",		// 회원가입
            "/user/login**","/user/login/**",		// 로그인
            "/user**"								// 유저정보조회 
            
    };
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        
        registry.addInterceptor(jwtInterceptor)			// customizing한 Interceptor 추가 
                .addPathPatterns(INCLUDE_PATHS)			// token 인증과정을 거칠 url 패턴 추가.   
        		.excludePathPatterns(EXCLUDE_PATHS);	// 인증 필요없는 url 패턴 추가.			
    }
}
