package com.ssafy.newbit.config;
/*
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.newbit.model.service.UserService;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
		if (userService.checkLogin(user.get("userEmail"), user.get("userPassword")) == true) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
        return userRepository.findByEmail(username);
                //.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}*/
