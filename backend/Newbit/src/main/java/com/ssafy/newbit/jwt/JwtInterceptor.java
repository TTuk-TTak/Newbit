package com.ssafy.newbit.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

	@Autowired
	private JwtProvider jwtProvider;
	

	// 토큰 검증이 실행됨
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//final String token = request.getHeader(HEADER_AUTH);					// token 헤더의 key 지정해줌
		String token = jwtProvider.resolveToken((HttpServletRequest) request);
		
		// 토큰이 유효하지 않은 경우
		if(token == null || !(jwtProvider.checkClaim(token))) { 
			logger.info("유효하지 않은 토큰");											
			throw new JwtUnAuthorizedException();
			//response.sendRedirect("/login/");									// 필요 시, 이곳에서 '권한 거부되었을때 redirect' 수행
		// 권한이 없는 사용자일 경우
		}else if(!((jwtProvider.getJwtContents(token).get("roles")).equals("User"))) {
			logger.info("권한 없음");
			throw new JwtAccessDeniedException();
		// 인증 & 권한 모두 완료된 사용자
		}else{ 
			logger.info("올바른 토큰 & 권한있음"); 
			return true;
		}

	}
	
}