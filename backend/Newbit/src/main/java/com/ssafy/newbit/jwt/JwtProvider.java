package com.ssafy.newbit.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.controller.PostController;

@RequiredArgsConstructor
@Component
@RestController
public class JwtProvider {
	
	private static final String secretKey =  "secretKey"; //Token 체크시 필요한 암호키  *** 
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	// 토큰 유효시간 설정 ***
    private long tokenValidTime = 30 * 60 * 1000L;	// = 30분
    
    // JWT 토큰 생성
    public String createToken(String userPk, String roles) {      
        Date now = new Date();
        
        // Claims = payload 에 속하는 정보
        Claims claims = Jwts.claims()
        		.setSubject(userPk); 
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        
        String jwt = Jwts.builder()
        		.setHeaderParam("typ", "JWT")							   // Header 세팅
                .setClaims(claims) 										   // Claim 세팅
                .setIssuedAt(now) 										   // 토큰 발행 시간 
                .setExpiration(new Date(now.getTime() + tokenValidTime))   // 토큰 유효시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())  // 사용할 암호화 알고리즘 & secretKey
                .compact();
        
        return jwt;
    }
    
	// Header의 key 값 설정.(JwtInterceptor에서 사용) // "X-AUTH-TOKEN" : "TOKEN값'  for Postman  
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

	
	// Token 유효성 확인 
	public boolean checkClaim(String jwt) {
	    try {
	        Claims claims = Jwts.parser()
	            .setSigningKey(secretKey.getBytes())
	            .parseClaimsJws(jwt).getBody(); 
	        return true;
	    
	    }catch(ExpiredJwtException e) {   		
	        logger.error("Expired Jwt token");
	        System.out.println("유효기간이 만료된 Jwt 토큰입니다 [Expired Jwt token]");
	    
	    }catch(UnsupportedJwtException e) {         
	        logger.error("Unsupported Jwt token");
	        System.out.println("지원되지 않는 형식의 Jwt 토큰입니다 [Unsupported Jwt token]");
	        
	    }catch(MalformedJwtException e) {         
	        logger.error("Malformed Jwt token");
	        System.out.println("구조적으로 올바르지 않은 위조된 Jwt 토큰입니다 [Malformed Jwt token]");
	        
	    }catch(SignatureException e) {         
	        logger.error("Wrong Signature Jwt token");
	        System.out.println("잘못된  Jwt 서명입니다 [Wrong Signature Jwt token]");
	        
	    }catch(JwtException e) {         //그 외 Token 에러 
	        logger.error("Token Error");
	        System.out.println("token error");
	    }
	    return false;
	}

    
	// Token 정보 추출 - payload(body) -회원정보
	public Claims getJwtContents(String jwt) {		// Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
	    Claims claims = Jwts.parser()
	        .setSigningKey(secretKey.getBytes())
	        .parseClaimsJws(jwt)
	        .getBody();	
	    return claims;
	}

	// 삭제할지 결정!
	@Getter
    public class TokenInfo {
        private String username;
        private Date issuedAt;
        private Date expire;

        public TokenInfo(String username, Date issuedAt, Date expire) {
            this.username = username;
            this.issuedAt = issuedAt;
            this.expire = expire;
        }
    }

}


