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

import com.ssafy.newbit.model.DailyDataDto;
import com.ssafy.newbit.model.DailyGraphDto;
import com.ssafy.newbit.model.RadarGraphDto;
import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.service.GraphService;
import com.ssafy.newbit.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/graph")
@Api("그래프 컨트롤러  API")
public class GraphController {

	public static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final int VERTEX = 6;
	private static final int TERM = 150;
	private static String[] key = { "java", "python", "javascript", "react", "vuejs", "angular", "android", "ios",
			"uiux", "nodejs", "php", "database", "springboot", "git", "infrastructure", "aiml", "generaldev",
			"webdev" };

	@Autowired
	private GraphService graphService;

	// 레이더 그래프에 필요한 정보 조회
	@ApiOperation(value = "레이더 그래프 정보 조회", notes = "회원 코드에 해당하는 회원의 팔로우 리스트를 반환한다.", response = RadarGraphDto.class)
	@GetMapping("/radar")
	public ResponseEntity<RadarGraphDto> radarGraph(@RequestParam("uid") int uid) throws Exception {
		logger.info("radarGraph - 호출 : " + uid);

		List<String> keywordList = graphService.getKeyword(uid);
		Map<String, Integer> map = new HashMap<>();

		// 기존 키워드 목록에 해당하는 것만
		for (String s : key)
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

	@ApiOperation(value = "레이더 그래프 정보 조회", notes = "회원 코드에 해당하는 회원의 팔로우 리스트를 반환한다.", response = RadarGraphDto.class)
		@GetMapping("/daily")
		public ResponseEntity<DailyGraphDto> dailyGraph(@RequestParam("uid") int uid) throws Exception {
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				
		//탐색 종료 날자 구하기
		cal.add(Calendar.DATE, (-1 * TERM));
		String enddate = df.format(cal.getTime()).toString();
		map.put("enddate",enddate);
	
		//DB에서 날짜별 활동 카운트, 날짜 오름차순 
		List<DailyDataDto> data = graphService.getDailyData(map);
//		for(DailyDataDto d : data)
//			System.out.println(d.toString());
		
		//날짜별로 보내기 위한 리스트 배열 (day[0] : 일요일 리스트, day[1] : 월요일 리스트)
		List<Integer>[] day = new ArrayList[7];
		for(int i =0;i<7;i++) 
			day[i] = new ArrayList<>();
		
		//150일 전 시작 날짜
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK)-1; //0 : 일요일, 1 : 월요일
        int dataidx = 0;
        
        for(int i=0;i<=TERM;i++) {
        	String temp_date = df.format(cal.getTime()).toString();
        	
        	//DB에서 활동 기록 카운팅 한 날짜 중 있으면 
        	if(dataidx < data.size() && temp_date.equals(data.get(dataidx).getDate())) {
        		day[dayofweek].add(data.get(dataidx).getCount());
        		dataidx++;
        	}else {	//없으면 0
        		day[dayofweek].add(0);
        	}
      
        	//요일 증가
        	dayofweek  = (dayofweek+1)%7;     
        	//하루 증가
        	cal.add(Calendar.DATE, 1);
        }

		DailyGraphDto dailyGraphDto = new DailyGraphDto(day[0], day[1], day[2], day[3], day[4], day[5], day[6]);
		return new ResponseEntity<DailyGraphDto>(dailyGraphDto, HttpStatus.OK);
		
	}
}
