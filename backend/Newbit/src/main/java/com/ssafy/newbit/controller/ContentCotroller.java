package com.ssafy.newbit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.ContentDto;
import com.ssafy.newbit.model.service.ContentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/content")
@Api("콘텐츠 컨트롤러  API")
public class ContentCotroller {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private ContentService contentService;

	@GetMapping
	@ApiOperation(value = "특정 콘텐츠 상세 조회", notes = "콘텐츠 번호에 해당하는 콘텐츠 내용을 반환", response = ContentDto.class)
	public ResponseEntity<ContentDto> getPost(
			@RequestParam @ApiParam(value = "특정 콘텐츠를 가져오기 위한 콘텐츠 번호", required = true) int cid) throws Exception {
		logger.info("getContent 호출 : " + cid);
		return new ResponseEntity<ContentDto>(contentService.getContent(cid), HttpStatus.OK);
	}
	
	
}
