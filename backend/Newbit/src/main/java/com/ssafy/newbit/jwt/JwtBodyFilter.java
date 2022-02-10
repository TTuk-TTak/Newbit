package com.ssafy.newbit.jwt;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class JwtBodyFilter implements Filter {

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) 
  	throws IOException, ServletException {
    try {
      // ReadableRequestBodyWrapper 에서 저장한 Request 사용
    	JwtRequestBodyWrapper wrapper = new JwtRequestBodyWrapper((HttpServletRequest) request);
      // Attribute 직접 만들어 사용
      wrapper.setAttribute("requestBody", wrapper.getRequestBody());  
      chain.doFilter(wrapper, response);
    } catch (Exception e) {
      chain.doFilter(request, response);
    }
  }

}