package com.ssafy.newbit.jwt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
	//private static final String HEADER_AUTH = "Authorization";
	
	@Autowired
	private JwtProvider jwtProvider;
	

	// 토큰 검증이 실행됨
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("토큰 Interceptor 거침!!!!!!!!!");
		logger.info("토큰 Interceptor 거침!!!!!!!!!");	
		//final String token = request.getHeader(HEADER_AUTH);					// token 헤더의 key 지정해줌
		String token = jwtProvider.resolveToken((HttpServletRequest) request);
		String userCode = "";
		
		String method = request.getMethod();
		// GET 방식 일 때, 
		if(method.equals("GET")||method.equals("DELETE")||method.equals("POST")||method.equals("PATCH")) {//method.equals("GET")||method.equals("DELETE")
			// Get 방식 일 때만, 쿼리문 가져옴
			String url = request.getQueryString();			// 다른 방식 or 쿼리 아닐때 오류 안나나 확인 ! 
			//System.out.println("url은 이거다"+url);
			
			
			// 정규식으로 uid 값 가져오기 
			String pattern = "^(uid)=([0-9]*)(.*)$";			// 정규식 :쿼리값 가져옴  //"^(uid)=(.*)$";
			Pattern cp = Pattern.compile(pattern);		// 정규식 패턴화 
			Matcher matcher = cp.matcher(url);
			
			while (matcher.find()) {
				if (matcher.group(2) != null) { 		// group(2) : 원하는 값에 따라 변경
					userCode = matcher.group(2);
					break;
		    	}
			}
			/*
			int matchCount = 0;
			while (matcher.find()) {
				if (matcher.group(matchCount).matches("^\\d{2}$")) { 		// group(2) : 원하는 값에 따라 변경
					userCode = matcher.group(matchCount);
					break;
		    	}
				System.out.println(matchCount + " : " + matcher.group());
				matchCount++;
			}*/
			//System.out.println("예상 사용자는 이거다"+userCode);
		}
		// 다른 방식 일 경우 정의 
		
		
		// 토큰이 유효하지 않은 경우
		if(token == null || !(jwtProvider.checkClaim(token)) ||token.equals("")) { 
			logger.info("유효하지 않은 토큰");											
			throw new JwtUnAuthorizedException();
			//response.sendRedirect("/login/");									// 필요 시, 이곳에서 '권한 거부되었을때 redirect' 수행
		// 권한이 없는 사용자일 경우
		}else if(!((jwtProvider.getJwtContents(token).get("roles")).equals(userCode))) {
			logger.info("권한 없음");
			throw new JwtAccessDeniedException();
		// 인증 & 권한 모두 완료된 사용자
		}else{ 
			logger.info("올바른 토큰 & 권한있음"); 
			//response.getWriter().write(token);
			return true;
		}

	}
	
}