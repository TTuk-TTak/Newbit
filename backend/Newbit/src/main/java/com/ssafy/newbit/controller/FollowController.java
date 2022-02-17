package com.ssafy.newbit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = { "http://localhost:8080", "http://i6a101.p.ssafy.io:8080"})
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
	
	// 팔로워 알림 조회
	@GetMapping("/notification")
	public ResponseEntity<List<UserDto>> getFollowNoti(@RequestParam("uid") int userCode) throws Exception {
		logger.info("getFollowNoti - 호출 : " + userCode);
		return new ResponseEntity<List<UserDto>>(userService.getFollowNoti(userCode), HttpStatus.OK);
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
			@RequestParam @ApiParam(value = "팔로잉한 유저(나) 코드와 팔로잉 삭제할 유저 코드", required = true) int from, int to)
			throws Exception {
		logger.info("deleteLikePost 호출 : ");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("from", from);
		map.put("to", to);
		if (userService.unfollowUser(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	// 팔로잉 추천
	@ApiOperation(value = "팔로잉 추천", notes = "나와 비슷한 관심사를 가진 유저를 팔로우하도록 추천해준다. 유저 목록을 반환한다.", response = UserDto.class)
	@GetMapping("/recommendation")
	public ResponseEntity<List<UserDto>> followRecommendation(
			@RequestParam @ApiParam(value = "팔로잉한 유저(나) 코드와 팔로잉 삭제할 유저 코드", required = true) int uid) throws Exception {

		// 현재 팔로잉하지 않고
		// 1개 이상 일치하는 관심 키워드를 가진
		// 유저 RCMCMT개 반환

		final int RCMCMT = 6;

		List<UserDto> list = new ArrayList<>();

		// 현재 팔로우하고 있는 유저를 제외하기 위한 팔로잉 목록을 불러와 유저 코드만 리스트로 추출
		List<UserDto> currentFollowing = userService.getFollowingList(uid);
		List<Integer> currentFollowingUsercode = new ArrayList<>();

		for (UserDto u : currentFollowing)
			currentFollowingUsercode.add(u.getUserCode());
		currentFollowingUsercode.add(uid);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("count", RCMCMT); // 가져올 개수
		map.put("followinglist", currentFollowingUsercode);

		// 현재 로그인한 유저 정보에서 키워드 가져오기
		UserDto me = userService.getUser(uid);
		String keyword = me.getUserKeyword();

		// 키워드 파싱
		List<String> keywordList = new ArrayList<>();

		if (keyword != null) {

			StringTokenizer st = new StringTokenizer(keyword, "_");
			while (st.hasMoreTokens()) {
				String str = st.nextToken();
				if (!str.equals("null"))
					keywordList.add(str);
			}
		}

		map.put("keywordlist", keywordList);

		list = userService.followRecommendation(map);

		// 키워드기반 추천으로 RCMDCNT명 이하의 유저가 뽑히면 부족한만큼 랜덤으로 추천
		if (list.size() < RCMCMT) {
			List<UserDto> random = userService.getRandomUser(map);
			while (list.size() < RCMCMT) {
				list.add(random.get(0));
				random.remove(0);
			}
		}

		return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
	}

}
