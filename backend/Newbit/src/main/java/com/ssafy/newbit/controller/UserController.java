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


//////////////////여기서 부터 직접 추가한 import
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ssafy.newbit.model.mapper.UserMapper;



@Api("유저 컨트롤러  API")
@CrossOrigin(origins = { "*" })
@RequestMapping("/user")		
@RestController
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;

	
	@ApiOperation(value = "사용자 추가", notes = "회원가입한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)	
	@PostMapping("/signup")
	public ResponseEntity<String> addUser(@RequestBody @ApiParam(value = "유저 정보.", required = true) UserDto userDto)throws Exception{  //@ApiParam(value = "유저 정보.", required = true)	//throws Exception
		if(userService.addUser(userDto)) { 
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "특정 사용자 조회", notes = "사용자 코드에 해당하는 사용자의 정보를 반환한다.", response = UserDto.class)
	@GetMapping("/{userCode}")
	public ResponseEntity<UserDto> getUser(@PathVariable("userCode") @ApiParam(value = "얻어올 사용자의 코드", required = true) int userCode)throws Exception{ 
		logger.info("getUser 호출 : " + userCode);
		return new ResponseEntity<UserDto>(userService.getUser(userCode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "아이디 중복 체크", notes = "중복된 아이디가 있는지 확인한다.", response = UserDto.class)
	@PostMapping("/idCheck")
	public ResponseEntity<String> checkId(@RequestParam("userId") @ApiParam(value = "입력받은 사용자의 아이디", required = true) String userId)throws Exception{ 
		logger.info("checkId 호출 : " + userId);
		if (userService.checkId(userId) == true) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "이메일 중복 체크", notes = "중복된 이메일이 있는지 확인한다.", response = UserDto.class)
	@PostMapping("/emailCheck")
	public ResponseEntity<String> checkEmail(@RequestParam("userEmail") @ApiParam(value = "입력받은 사용자의 이메일", required = true) String userEmail)throws Exception{ 
		logger.info("checkEmail 호출 : " + userEmail);
		if (userService.checkEmail(userEmail) == true) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
