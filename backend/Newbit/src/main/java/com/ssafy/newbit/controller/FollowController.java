package com.ssafy.newbit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	// 팔로워 리스트 조회
	@ApiOperation(value = "팔로워 리스트 조회", notes = "회원 코드에 해당하는 회원의 팔로워 리스트를 반환한다.", response = UserDto.class)
	@GetMapping("/follower")
	public ResponseEntity<List<UserDto>> getFollowerList(@RequestParam("uid") int userCode) throws Exception {
		logger.info("getFollowerList - 호출 : " + userCode);
		return new ResponseEntity<List<UserDto>>(userService.getFollowerList(userCode), HttpStatus.OK);
	}

	// 팔로잉 추가
	@ApiOperation(value = "팔로잉 추가", notes = "follow 테이블에 팔로잉하는 유저(from)-팔로잉할 유저(to) 코드 데이터 추가, 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> followUser(
			@RequestBody @ApiParam(value = "팔로잉하는 유저(나) 코드와 팔로잉할 유저 코드", required = true) HashMap<String, Integer> map)
			throws Exception {
		logger.info("followUser 호출");
		if (userService.followUser(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	// 팔로잉 삭제 
	@ApiOperation(value = "팔로잉 삭제", notes = "follow 테이블에서 팔로잉한 유저(from)-팔로잉 삭제할 유저(to) 코드 데이터를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping
	public ResponseEntity<String> unfollowUser(
			@RequestParam @ApiParam(value = "팔로잉한 유저(나) 코드와 팔로잉 삭제할 유저 코드", required = true) int from, int to) throws Exception {
		logger.info("deleteLikePost 호출 : ");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("from", from);
		map.put("to", to);
		if (userService.unfollowUser(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
}
