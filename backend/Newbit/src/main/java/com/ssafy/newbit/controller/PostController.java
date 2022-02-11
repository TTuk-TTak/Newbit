package com.ssafy.newbit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.service.PostService;
import com.ssafy.newbit.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;
import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.mapper.PostMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/post")
@Api("게시글 컨트롤러  API")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;

	@ApiOperation(value = "게시글 쓰기", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeArticle(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) PostDto postDto) throws Exception {
		if (postService.writePost(postDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "특정 게시글 조회", notes = "게시글 코드에 해당하는 게시글의 정보를 반환한다.", response = PostDto.class)
	@GetMapping
	public ResponseEntity<PostDto> getPost(
			@RequestParam @ApiParam(value = "얻어올 게시글의 코드", required = true) int uid, int pid)
			throws Exception {
		logger.info("getPost 호출 : " + pid);

		PostDto post = postService.getPost(pid);

		// 게시글 좋아요 스크랩 정보 추가
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("userCode", uid); // 현재 로그인한 유저의 아이디
		hm.put("postCode", pid);

		post.setLiked(postService.userLikePost(hm)); // 좋아요 여부 설정
		post.setScrapped(postService.userScrapPost(hm)); // 스크랩 여부 설정

		// 게시글 작성자 정보 추가
		UserDto u = userService.getUser(post.getUserCode());
		if (u != null) {
			post.setUserNick(u.getUserNick());
			post.setUserImg(u.getUserImg());
			post.setUserId(u.getUserId());
		}

		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	@GetMapping("/list")
	@ApiOperation(value = "소셜 피드 전체 게시글 조회", notes = "내가 쓰거나 공유한 글 + 내가 팔로우하는 사람들 글 정보를 반환", response = List.class)
	public ResponseEntity<List<PostDto>> listPost(
			@RequestParam @ApiParam(value = "소셜 피드 목록을 얻기 위한 정보", required = true) int uid, int lastpostcode, int size)
			throws Exception {
		logger.info("listPost 호출");

		// 팔로우할 목록 검색해서 파라미터로 넘겨줌
		List<Integer> followingList = postService.getFollowingList(uid);
		followingList.add(uid); // 본인 게시글 포함해서 가져와야 함

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("lastPostCode", lastpostcode); // 페이지네이션을 위한 마지막으로 본 게시글 번호
		map.put("size", size); // 한 페이지당 가져올 게시글의 개수

		map.put("followingList", followingList); // in 조건에 넣기 위한 검색해 올 유저의 코드가 담긴 리스트

		// 현재 로그인한 유저가 게시글에 대해 좋아요와 스크랩 했는지 표시하기 위한 코드
		// 게시글 작성자 정보 추가
		List<PostDto> list = postService.listPost(map);
		for (PostDto p : list) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("userCode", uid); // 현재 로그인한 유저의 아이디
			hm.put("postCode", p.getPostCode());

			p.setLiked(postService.userLikePost(hm)); // 좋아요 여부 설정
			p.setScrapped(postService.userScrapPost(hm)); // 스크랩 여부 설정

			// 게시글 작성자 정보

			System.out.println(p.getUserCode());
			UserDto u = userService.getUser(p.getUserCode());
			if (u != null) {
				p.setUserNick(u.getUserNick());
				p.setUserImg(u.getUserImg());
				p.setUserId(u.getUserId());
			}
		}

		return new ResponseEntity<List<PostDto>>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 유저 전체 게시글 조회", notes = "특정 유저가 쓰거나 공유한 모든 글 정보를 반환", response = List.class)
	@GetMapping("/user")
	public ResponseEntity<List<PostDto>> listUserPost(
			@RequestParam @ApiParam(value = "특정 유저의 게시글 목록을 얻기 위한 정보", required = true) int uid, int userid,
			int lastpostcode, int size) throws Exception {

		logger.info("listUserPost 호출 : " + userid);

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("userCode", userid);
		map.put("lastPostCode", lastpostcode);
		map.put("size", size);

		List<PostDto> list = postService.listUserPost(map);
		for (PostDto p : list) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("userCode", uid); // 현재 로그인한 유저의 아이디
			hm.put("postCode", p.getPostCode());

			p.setLiked(postService.userLikePost(hm)); // 좋아요 여부 설정
			p.setScrapped(postService.userScrapPost(hm)); // 스크랩 여부 설정

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

	@ApiOperation(value = "게시글 삭제", notes = "게시글코드에 해당하는 게시글 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{postCode}")
	public ResponseEntity<String> deletePost(
			@PathVariable("postCode") @ApiParam(value = "삭제할 게시글의 코드", required = true) int postCode) throws Exception {
		logger.info("deletePost 호출 : " + postCode);
		if (postService.deletePost(postCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 수정", notes = "게시글 내용(post_text)를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping
	public ResponseEntity<String> editPost(
			@RequestBody @ApiParam(value = "수정할 게시글의 내용과 게시글 코드에 해당하는 정보", required = true) PostTextDto PostTextDto)
			throws Exception {
		logger.info("editPost 호출" + PostTextDto.getPostCode());
		if (postService.editPost(PostTextDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@PostMapping("/like")
	@ApiOperation(value = "게시글 좋아요 추가", notes = "게시글 좋아요 테이블에 유저-좋아요한 게시글 코드 데이터 추가, 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	public ResponseEntity<String> likePost(
			@RequestBody @ApiParam(value = "좋아요할 사용자와 게시글 정보", required = true) HashMap<String, Integer> map)
			throws Exception {
		if (postService.likePost(map)) {
			map.put("count", 1);
			postService.updateLike(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@PostMapping("/scrap")
	@ApiOperation(value = "게시글 스크랩 추가", notes = "게시글 스크랩 테이블에 유저-스크랩한 게시글 코드 데이터 추가, 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	public ResponseEntity<String> scrapPost(
			@RequestBody @ApiParam(value = "	좋아요할 사용자와 게시글 정보", required = true) HashMap<String, Integer> map)
			throws Exception {
		if (postService.scrapPost(map)) {
			map.put("count", 1);
			postService.updateScrap(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

	}

	@ApiOperation(value = "게시글 좋아요 삭제", notes = "게시글코드에 해당하는 게시글 좋아요를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/like")
	public ResponseEntity<String> deleteLikePost(
			@RequestParam @ApiParam(value = "좋아요 삭제할 게시글의 코드", required = true) int uid, int pid) throws Exception {
		logger.info("deleteLikePost 호출 : ");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("uid", uid);
		map.put("pid", pid);
		map.put("count", -1);
		if (postService.deleteLikePost(map)) {
			postService.updateLike(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 스크랩 삭제", notes = "게시글코드에 해당하는 게시글 스크랩을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/scrap")
	public ResponseEntity<String> deleteScrapPost(
			@RequestParam @ApiParam(value = "스크랩 삭제할 게시글의 코드", required = true) int uid, int pid) throws Exception {
		logger.info("deleteScrapPost 호출 : ");
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("uid", uid);
		map.put("pid", pid);
		map.put("count", -1);
		if (postService.deleteScrapPost(map)) {
			postService.updateScrap(map);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	// 포스트 검색
	@ApiOperation(value = "포스트 검색", notes = "검색 키워드를 포함하는 게시글 목록을 반환", response = List.class)
	@GetMapping("/search")
	public ResponseEntity<List<PostDto>> searchPostList(
			@RequestParam @ApiParam(value = "콘텐츠 목록을 가져오기 위해 필요한 정보", required = true) String search, int uid,
			int lastpostcode, int size) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("search", search);
		map.put("lastPostCode", lastpostcode);
		map.put("size", size);
		logger.info("searchPostList 호출 : " + search);

		// 포스트 목록 불러오기
		List<PostDto> list = postService.searchPostList(map);

		// 현재 로그인한 유저가 포스트에 대해 좋아요와 스크랩 했는지 체크
		for (PostDto p : list) {
			HashMap<String, Object> hm = new HashMap<String, Object>();
			hm.put("userCode", uid);
			hm.put("postCode", p.getPostCode());
			p.setLiked(postService.userLikePost(hm));
			p.setScrapped(postService.userScrapPost(hm));

			// 게시글 작성자 정보 추가
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
