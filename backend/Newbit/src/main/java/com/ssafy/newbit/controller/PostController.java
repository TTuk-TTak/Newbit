package com.ssafy.newbit.controller;

<<<<<<< HEAD
=======
import java.util.HashMap;
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.service.PostService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;

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

<<<<<<< HEAD
	@ApiOperation(value = "게시글 쓰기", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)	
	@PostMapping
	public ResponseEntity<String> writeArticle(@RequestBody @ApiParam(value = "게시글 정보.", required = true) PostDto postDto) throws Exception {
=======
	@ApiOperation(value = "게시글 쓰기", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeArticle(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) PostDto postDto) throws Exception {
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
		if (postService.writePost(postDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

<<<<<<< HEAD
	
	@ApiOperation(value = "특정 게시글 조회", notes = "게시글 코드에 해당하는 게시글의 정보를 반환한다.", response = PostDto.class)
	@GetMapping("/{postCode}")
	public ResponseEntity<PostDto> getPost(@PathVariable("postCode") @ApiParam(value = "얻어올 게시글의 코드", required = true) int postCode) throws Exception {
		logger.info("getPost 호출 : " + postCode);
		return new ResponseEntity<PostDto>(postService.getPost(postCode), HttpStatus.OK);
	}
	

	@GetMapping
	@ApiOperation(value = "소셜 피드 전체 게시글 조회", notes = "내가 쓰거나 공유한 글 + 내가 팔로우하는 사람들 글 정보를 반환", response = List.class)
	public ResponseEntity<List<PostDto>> listPost(@RequestParam @ApiParam(value = "소셜 피드 목록을 얻기 위한 유저 코드", required = true) int uid) throws Exception {
		logger.info("listPost 호출");
		return new ResponseEntity<List<PostDto>>(postService.listPost(uid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 유저 전체 게시글 조회", notes = "특정 유저가 쓰거나 공유한 모든 글 정보를 반환", response = List.class)
	@GetMapping("/user")
	public ResponseEntity<List<PostDto>> listUserPost(@RequestParam @ApiParam(value = "조회할 특정 유저 코드", required = true) int uid) throws Exception {
		logger.info("listUserPost 호출 : "+uid);
		return new ResponseEntity<List<PostDto>>(postService.listUserPost(uid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 삭제", notes = "게시글코드에 해당하는 게시글 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{postCode}")
	public ResponseEntity<String> deletePost(@PathVariable("postCode") @ApiParam(value = "삭제할 게시글의 코드", required = true) int postCode) throws Exception {
=======
	@ApiOperation(value = "특정 게시글 조회", notes = "게시글 코드에 해당하는 게시글의 정보를 반환한다.", response = PostDto.class)
	@GetMapping("/{postCode}")
	public ResponseEntity<PostDto> getPost(
			@PathVariable("postCode") @ApiParam(value = "얻어올 게시글의 코드", required = true) int postCode) throws Exception {
		logger.info("getPost 호출 : " + postCode);
		return new ResponseEntity<PostDto>(postService.getPost(postCode), HttpStatus.OK);
	}

	@GetMapping
	@ApiOperation(value = "소셜 피드 전체 게시글 조회", notes = "내가 쓰거나 공유한 글 + 내가 팔로우하는 사람들 글 정보를 반환", response = List.class)
	public ResponseEntity<List<PostDto>> listPost(
			@RequestParam @ApiParam(value = "소셜 피드 목록을 얻기 위한 정보", required = true) int uid, int lastpostcode,
			int size) throws Exception {
		logger.info("listPost 호출");
		
		//팔로우할 목록 검색해서 파라미터로 넘겨줌
		List<Integer> followingList = postService.getFollowingList(uid);
		followingList.add(uid); //본인 게시글 포함해서 가져와야 함
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("lastPostCode", lastpostcode);
		map.put("size", size);
		
		map.put("followingList", followingList); //in 조건에 넣기 위한 검색해 올 유저의 코드가 담긴 리스트
		
		return new ResponseEntity<List<PostDto>>(postService.listPost(map), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 유저 전체 게시글 조회", notes = "특정 유저가 쓰거나 공유한 모든 글 정보를 반환", response = List.class)
	@GetMapping("/user")
	public ResponseEntity<List<PostDto>> listUserPost(
			@RequestParam @ApiParam(value = "특정 유저의 게시글 목록을 얻기 위한 정보", required = true) int uid, int lastpostcode,
			int size) throws Exception {		
		
		logger.info("listUserPost 호출 : " + uid);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("userCode", uid);
		map.put("lastPostCode", lastpostcode);
		map.put("size", size);
		
		return new ResponseEntity<List<PostDto>>(postService.listUserPost(map), HttpStatus.OK);
	}

	@ApiOperation(value = "게시글 삭제", notes = "게시글코드에 해당하는 게시글 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{postCode}")
	public ResponseEntity<String> deletePost(
			@PathVariable("postCode") @ApiParam(value = "삭제할 게시글의 코드", required = true) int postCode) throws Exception {
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
		logger.info("deletePost 호출 : " + postCode);
		if (postService.deletePost(postCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
<<<<<<< HEAD
	
	@ApiOperation(value = "게시글 수정", notes = "게시글 내용(post_text)를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping
	public ResponseEntity<String> editPost(@RequestBody @ApiParam(value = "수정할 게시글의 내용과 게시글 코드에 해당하는 정보", required = true) PostTextDto PostTextDto) throws Exception{
		logger.info("editPost 호출" + PostTextDto.getPostCode());
		if(postService.editPost(PostTextDto)) {
=======

	@ApiOperation(value = "게시글 수정", notes = "게시글 내용(post_text)를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping
	public ResponseEntity<String> editPost(
			@RequestBody @ApiParam(value = "수정할 게시글의 내용과 게시글 코드에 해당하는 정보", required = true) PostTextDto PostTextDto)
			throws Exception {
		logger.info("editPost 호출" + PostTextDto.getPostCode());
		if (postService.editPost(PostTextDto)) {
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
