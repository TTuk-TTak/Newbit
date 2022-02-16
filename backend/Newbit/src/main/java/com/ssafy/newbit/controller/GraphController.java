package com.ssafy.newbit.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
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

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/graph")
@Api("그래프 컨트롤러  API")
public class GraphController {

	public static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final int VERTEX =6;
	private static String[] key = 
		{ 
			"java", 
			"python", 
			"javascript", 
			"react", 
			"vuejs", 
			"angular",
			"android",
			"ios",
			"uiux",
			"nodejs",
			"php",
			"database",
			"springboot",
			"git",
			"infrastructure",
			"aiml",
			"generaldev",
			"webdev"
		};

	@Autowired
	private GraphService graphService;

	// 레이더 그래프에 필요한 정보 조회
	@ApiOperation(value = "레이더 그래프 정보 조회", notes = "회원 코드에 해당하는 회원의 팔로우 리스트를 반환한다.", response = RadarGraphDto.class)
	@GetMapping("/radar")
	public ResponseEntity<RadarGraphDto> radarGraph(@RequestParam("uid") int uid) throws Exception {
		logger.info("radarGraph - 호출 : " + uid);

		List<String> keywordList = graphService.getKeyword(uid);
		Map<String, Integer> map = new HashMap<>();

		//기존 키워드 목록에 해당하는 것만 
		for(String s : key)
			map.put(s, 0);
		
		// 키워드별로 파싱해서 카운트
		for (String s : keywordList) {
			StringTokenizer st = new StringTokenizer(s, "_");
			while (st.hasMoreTokens()) {
				String str = st.nextToken();
				if (!str.equals("null")) { 		
					if (map.containsKey(str))
						map.put(str, map.get(str) + 1);
				}
			}
		}

		// map 내림차순 정렬
		List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
		entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});

		List<String> category = new ArrayList<>();
		List<Integer> data = new ArrayList<>();

		for (int i = 0; i < VERTEX; i++) { 
			Map.Entry<String, Integer> entry = entryList.get(i);
			category.add(entry.getKey());
			data.add(entry.getValue());
//			System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
		} 

		return new ResponseEntity<RadarGraphDto>(new RadarGraphDto(category, data), HttpStatus.OK);
	}

}
