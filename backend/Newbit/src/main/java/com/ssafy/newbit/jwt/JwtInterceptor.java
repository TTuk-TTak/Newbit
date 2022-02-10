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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import com.ssafy.newbit.jwt.exception.JwtAccessDeniedException;
import com.ssafy.newbit.jwt.exception.JwtUnAuthorizedException;

import io.jsonwebtoken.lang.Collections;

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
		/*	사용자 검증 로직	*/
		
		//final String token = request.getHeader(HEADER_AUTH);					// token 헤더의 key 지정해줌
		System.out.println("토큰 Interceptor 거침!!!!!!!!!");
		logger.info("~ 인증필요: JwtInterceptor 거쳐감 ~");	
		
		// 해더에서 token 추출
		String token = jwtProvider.resolveToken((HttpServletRequest) request);
		String userCode = "";
		
		// url에서 쿼리문 추출
		String params = request.getQueryString();		// 쿼리 못받아 올 시, null 반환
		
		// @RequestParam 방식 : 쿼리문 있을 때  ->  url 에서 추출 
		if(params != null) {
			System.out.println("@RequestParam 방식으로 진행합니다" );
			
			// 정규식으로 uid 값 가져오기 
			String pattern = "^(uid)=([0-9]*)(.*)$";	// 정규식 :쿼리값 가져옴  
			Pattern cp = Pattern.compile(pattern);		// 정규식 패턴화 
			Matcher matcher = cp.matcher(params);	
			
			while (matcher.find()) {
				if (matcher.group(2) != null) { 		// group(2) : 원하는 값에 따라 변경
					userCode = matcher.group(2);
					break;
		    	}
			}

		// @RequestBody 방식 : 쿼리문 없을 때   -> Body의 Json데이터 가져옴
		}else {
			System.out.println("@RequestBody 방식으로 진행합니다");
			
			// 커스터마이징한 attribute로 body 데이터 가져옴 (CopyBodyFilter & ReadableRequestBodyWrapper)
			String Body = (String) request.getAttribute("requestBody");		
			// String to map 
			ObjectMapper mapper = new ObjectMapper();
			Map<String, String> map = mapper.readValue(Body, Map.class);
			
			userCode = map.get("userCode");
			
		}
		
		
		
		
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