package com.ssafy.newbit.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@Autowired
	private ContentService contentService;

	@GetMapping
	@ApiOperation(value = "특정 콘텐츠 상세 조회", notes = "콘텐츠 번호에 해당하는 콘텐츠 내용을 반환", response = ContentDto.class)
	public ResponseEntity<ContentDto> getPost(
			@RequestParam @ApiParam(value = "특정 콘텐츠를 가져오기 위한 콘텐츠 번호", required = true) int cid) throws Exception {
		logger.info("getContent 호출 : " + cid);
		return new ResponseEntity<ContentDto>(contentService.getContent(cid), HttpStatus.OK);
	}

	@ApiOperation(value = "추천 피드 콘텐츠 조회", notes = "키워드에 해당하는 콘텐츠 목록을 정렬 기준으로 반환", response = List.class)
	@GetMapping("/list")
	public ResponseEntity<List<ContentDto>> newContentList(
			@RequestParam @ApiParam(value = "게시글 목록을 가져오기 위해 필요한 정보", required = true)String sorting, int uid, int lastcontentcode, int size, String keyword) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		List<String> keywordList = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(keyword, "_");
		while (st.hasMoreTokens()) {
			String str = st.nextToken();
			if (!str.equals("null"))
				keywordList.add(str);
		}
		map.put("keywordList", keywordList);
		map.put("type", sorting);
		
		map.put("lastContentCode", lastcontentcode);
		map.put("size",size);
		logger.info("newContentList 호출 : " + keyword);

		//콘텐츠 목록 불러오기
		List<ContentDto> list = contentService.newListContent(map);
		
		
		//현재 로그인한 유저가 콘텐츠에 대해 좋아요와 스크랩 했는지 체크
		for(ContentDto c : list) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("userCode", uid);
			hm.put("contentCode", c.getContentCode());
			c.setLiked(contentService.userLikeContent(hm));
			c.setScrapped(contentService.userScrapContent(hm));
		}
		
		return new ResponseEntity<List<ContentDto>>(list, HttpStatus.OK);
	}

	@PostMapping("/like")
	@ApiOperation(value ="콘텐츠 좋아요 추가", notes ="콘텐츠 좋아요 테이블에 유저-좋아요한 콘텐츠 코드 데이터 추가, 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	public ResponseEntity<String> likeContent(
			@RequestBody @ApiParam(value = "좋아요할 사용자와 콘텐츠 정보", required = true) HashMap<String, Integer> map)
			throws Exception {
		if (contentService.likeContent(map)) {
			map.put("count", 1);
			contentService.updateLike(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	@PostMapping("/scrap")
	@ApiOperation(value = "콘텐츠 스크랩 추가", notes = "콘텐츠 스크랩 테이블에 유저-스크랩한 콘텐츠 코드 데이터 추가, 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	public ResponseEntity<String> scrapContent(
			@RequestBody @ApiParam(value = "좋아요할 사용자와 콘텐츠 정보", required = true) HashMap<String, Integer> map)
			throws Exception {
		if (contentService.scrapContent(map)) {
			map.put("count", 1);
			contentService.updateScrap(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	@ApiOperation(value = "콘텐츠 좋아요 삭제", notes = "콘텐츠코드에 해당하는 콘텐츠 좋아요를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/like")
	public ResponseEntity<String> deleteLikeContent(
			@RequestParam @ApiParam(value = "좋아요 삭제할 콘텐츠의 코드", required = true) int uid, int cid) throws Exception {
		logger.info("deleteLikeContent 호출 : ");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("uid", uid);
		map.put("cid", cid);
		map.put("count", -1);
		if (contentService.deleteLikeContent(map)) {
			contentService.updateLike(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "콘텐츠 스크랩 삭제", notes = "콘텐츠 코드에 해당하는 콘텐츠 스크랩을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/scrap")
	public ResponseEntity<String> deleteScrapContent(
			@RequestParam @ApiParam(value = "스크랩 삭제할 콘텐츠의 코드", required = true) int uid, int cid) throws Exception {
		logger.info("deleteScrapContent 호출 : ");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("uid", uid);
		map.put("pid", cid);
		map.put("count", -1);
		if (contentService.deleteScrapContent(map)) {
			contentService.updateScrap(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@PostMapping("/read")
	@ApiOperation(value = "콘텐츠 읽음 추가", notes = "콘텐츠 read 테이블에 콘텐츠 코드 - 유저 코드 추가, 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	public ResponseEntity<String> readContent(
			@RequestBody @ApiParam(value = "읽음 처리할 사용자와 콘텐츠 정보", required = true) HashMap<String, Integer> map)
			throws Exception {
		if (contentService.readContent(map)) {
			map.put("count", 1);
			contentService.updateRead(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}
}
