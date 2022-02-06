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
	private final JwtInterceptor jwtInterceptor;
	
    private static final String[] EXCLUDE_PATHS = {
    		// 제외할 경로 지정 
            "/**"
    };
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        
        registry.addInterceptor(jwtInterceptor)			// customizing한 Interceptor 추가 
                .addPathPatterns("/user/jwttest/**")		// token 인증과정을 거칠 url 패턴 추가.
        		.excludePathPatterns("/user/login/**");	// 인증 필요없는 url 패턴 추가.			//EXCLUDE_PATHS
    }
}