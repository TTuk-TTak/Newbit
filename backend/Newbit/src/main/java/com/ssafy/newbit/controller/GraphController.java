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

import com.ssafy.newbit.model.RadarGraphDto;
import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.service.GraphService;
import com.ssafy.newbit.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "http://localhost:8080"})
@RestController
@RequestMapping("/graph")
@Api("그래프 컨트롤러  API")
public class GraphController {

	public static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private GraphService graphService;

	// 레이더 그래프에 필요한 정보 조회
	@ApiOperation(value = "레이더 그래프 정보 조회", notes = "회원 코드에 해당하는 회원의 팔로우 리스트를 반환한다.", response = RadarGraphDto.class)
	@GetMapping("/radar")
	public ResponseEntity<List<RadarGraphDto>> radarGraph(@RequestParam("uid") int uid) throws Exception {
		logger.info("radarGraph - 호출 : " + uid);
		List<RadarGraphDto> list = new ArrayList<>();
		
		return new ResponseEntity<List<RadarGraphDto>>(list, HttpStatus.OK);
	}


}
