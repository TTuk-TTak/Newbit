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
@RequestMapping("/follow")
@Api("팔로우 컨트롤러  API")
public class FollowController {
	
	public static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;

	// 팔로우 리스트 조회
	@ApiOperation(value = "팔로우 리스트 조회", notes = "회원 코드에 해당하는 회원의 팔로우 리스트를 반환한다.", response = UserDto.class)
	@GetMapping("/following")
	public ResponseEntity<List<UserDto>> getFollowingList(@RequestParam("uid") int userCode) throws Exception {
		logger.info("getFollowingList - 호출 : " + userCode);
		return new ResponseEntity<List<UserDto>>(userService.getFollowingList(userCode), HttpStatus.OK);
	}
	
}
