package com.ssafy.newbit.jwt.exception;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Data Not Found")
public class JwtAccessDeniedException extends RuntimeException{
	private static final long serialVersionUID = -2082731243513674125L;

	public JwtAccessDeniedException() {
		super("[403]필요한 권한이 없음.");
	}

}
