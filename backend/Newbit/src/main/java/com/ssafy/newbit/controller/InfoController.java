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
import com.ssafy.newbit.model.InfoDto;
import com.ssafy.newbit.model.RadarGraphDto;
import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.service.GraphService;
import com.ssafy.newbit.model.service.InfoService;
import com.ssafy.newbit.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

@CrossOrigin(origins = { "http://localhost:8080", "https://i6a101.p.ssafy.io"})
@RestController
@RequestMapping("/info")
public class InfoController {

	public static final Logger logger = LoggerFactory.getLogger(FollowController.class);
	@Autowired
	private InfoService infoService;

	@GetMapping
	public ResponseEntity<InfoDto> getInfo() throws Exception {
		int members = infoService.getMembers();
		int posts = infoService.getPosts();
		int contents = infoService.getContents();
		InfoDto info = new InfoDto(members, posts, contents);
		return new ResponseEntity<InfoDto>(info, HttpStatus.OK);
	}
}
 	