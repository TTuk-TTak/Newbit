package com.ssafy.newbit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.NotiDto;
import com.ssafy.newbit.model.service.NotiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;



@CrossOrigin(origins = { "http://localhost:8080", "https://i6a101.p.ssafy.io"})
@RequestMapping("/noti")		
@RestController
public class NotiController{

	public static final Logger logger = LoggerFactory.getLogger(NotiController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private NotiService notiService;
	
	// 팔로워 알림 조회
	@GetMapping
	public ResponseEntity<List<NotiDto>> getNoti(@RequestParam("uid") int userCode) throws Exception {
		logger.info("getNoti - 호출 : " + userCode);
		return new ResponseEntity<List<NotiDto>>(notiService.getNoti(userCode), HttpStatus.OK);
	}
	
}
