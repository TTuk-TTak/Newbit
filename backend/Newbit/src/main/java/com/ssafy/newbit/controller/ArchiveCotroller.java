package com.ssafy.newbit.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.ContentDto;
import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.service.ArchiveService;
import com.ssafy.newbit.model.service.ContentService;
import com.ssafy.newbit.model.service.PostService;
import com.ssafy.newbit.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/scrap")
@Api("아카이브 컨트롤러  API")
public class ArchiveCotroller {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ArchiveService archiveService;
	@Autowired
	private ContentService contentService;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;

	@ApiOperation(value = "아카이빙 - 스크랩한 콘텐츠 목록 조회", notes = "유저가 스크랩한 콘텐츠 목록을 반환", response = List.class)
	@GetMapping("/content")
	public ResponseEntity<List<ContentDto>> contentScrapList(
			@RequestParam @ApiParam(value = "최신 게시글 목록을 가져오기 위해 필요한 정보", required = true) int uid, int lastcontentcode,
			int size, String keyword) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		logger.info("contentScrapList 호출 : " + keyword);

		// 키워드 처리하는 부분
		List<String> keywordList = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(keyword, "_");
		while (st.hasMoreTokens()) {
			String str = st.nextToken();
			if (!str.equals("null"))
				keywordList.add(str);
		}
		map.put("keywordList", keywordList);

		// 스크랩한 목록 받아오는 부분
		List<Integer> contentScrapList = archiveService.getContentScrapList(uid);
		map.put("contentScrapList", contentScrapList);

		map.put("userCode", uid);
		map.put("lastContentCode", lastcontentcode);
		map.put("size", size);

		// 커서 기반 페이지네이션을 위한 커서 생성, listContent()호출할 때 커서로 넣어줌
		if (lastcontentcode != 0) {
			HashMap<String, Object> cursormap = new HashMap<String, Object>();
			cursormap.put("contentCode", lastcontentcode);
			cursormap.put("type", "new");
			long cursor = contentService.getCursor(cursormap);
			map.put("cursor", cursor);
			// System.out.println(cursor);
		}

		List<ContentDto> list = archiveService.listContent(map);

		// 현재 로그인한 유저가 콘텐츠에 대해 좋아요와 스크랩 했는지 체크
		for (ContentDto c : list) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("userCode", uid);
			hm.put("contentCode", c.getContentCode());
			c.setLiked(contentService.userLikeContent(hm));
			c.setRead(contentService.userReadContent(hm));
			c.setScrapped(true);
		}

		return new ResponseEntity<List<ContentDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "아카이빙 - 스크랩한 게시글 목록 조회", notes = "유저가 스크랩한 게시글 목록을 반환", response = List.class)
	@GetMapping("/post")
	public ResponseEntity<List<PostDto>> postScrapList(
			@RequestParam @ApiParam(value = "스크랩한 게시글 목록을 얻기 위한 정보", required = true) int uid, int lastpostcode,
			int size) throws Exception {

		logger.info("postScrapList 호출 : ");

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("userCode", uid);
		map.put("lastPostCode", lastpostcode);
		map.put("size", size);

		// 스크랩한 목록 받아오는 부분
		List<Integer> postScrapList = archiveService.getPostScrapList(uid);
		map.put("postScrapList", postScrapList);

		List<PostDto> list = archiveService.listPost(map);
		System.out.println(list.size());
		// 현재 로그인한 유저가 게시글에 대해 좋아요와 스크랩 했는지 체크
		for (PostDto p : list) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("userCode", uid); // 현재 로그인한 유저의 아이디
			hm.put("postCode", p.getPostCode());

			p.setLiked(postService.userLikePost(hm)); // 좋아요 여부 설정
			p.setScrapped(true); // 스크랩 여부 설정

			// 게시글 작성자 정보
			UserDto u = userService.getUser(p.getUserCode());
			if (u != null) {
				p.setUserNick(u.getUserNick());
				p.setUserImg(u.getUserImg());
				p.setUserId(u.getUserId());
			}
		}

		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

}
