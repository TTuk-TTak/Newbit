package com.ssafy.newbit.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("유저 컨트롤러  API")
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;

	// 회원정보 수정
	@ApiOperation(value = "회원 정보 수정", notes = "수정할 회원 정보를 입력한다. DB 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping
	public ResponseEntity<String> editUserInfo(@RequestBody @ApiParam(value = "수정 가능한 정보 : 아이디, 닉네임, 비밀번호, 한줄 소개, 프로필 사진, 관심 키워드", required = true) UserDto userDto) throws Exception {
		logger.info("editUserInfo - 호출");
		System.out.println(userService.editUserInfo(userDto));
		if (userService.editUserInfo(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	// 회원 정보 조회
	@ApiOperation(value = "회원 정보 조회", notes = "회원 코드에 해당하는 회원의 정보를 반환한다.", response = UserDto.class)
	@GetMapping
	public ResponseEntity<UserDto> getUser(@RequestParam("uid") int userCode) throws Exception {
		logger.info("getUser - 호출 : " + userCode);
		return new ResponseEntity<UserDto>(userService.getUser(userCode), HttpStatus.OK);
	}
}
