package com.ssafy.newbit.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Data Not Found")
public class JwtUnAuthorizedException extends RuntimeException {
	private static final long serialVersionUID = -2238030302650813813L;
	
	public JwtUnAuthorizedException() {
		super("[401]유효한 자격증명 없이 접근하려 함.");
	}
}