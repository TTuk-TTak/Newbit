package com.ssafy.newbit.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.service.CommentService;
import com.ssafy.newbit.model.service.PostService;
import com.ssafy.newbit.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.ssafy.newbit.model.CommentDto;
import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.UserDto;

import java.util.HashMap;
import java.util.List;

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

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/comment")
@Api("댓글 컨트롤러  API")
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PostService postService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "댓글 쓰기", notes = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)	
	@PostMapping
	public ResponseEntity<String> writeComment(@RequestBody @ApiParam(value = "댓글 정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("writeComment 호출 : " + commentDto.getCommentText());
		if (commentService.writeComment(commentDto)) {
			
			//댓글 작성하면 댓글이 달린 게시글의 post_comment 1 증가 
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("postCode", commentDto.getPostCode());
			map.put("count", 1);
			postService.updateComment(map);
			
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<List<CommentDto>> listComment(@RequestParam @ApiParam(value = "해당 게시글 코드에 달린 모든 댓글 반환", required = true) int pid) throws Exception {
		logger.info("listComment 호출 : "+pid);
 		return new ResponseEntity<List<CommentDto>>(commentService.listComment(pid), HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 삭제", notes = "댓글코드에 해당하는 게시글 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{commentCode}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentCode") @ApiParam(value = "삭제할 게시글의 코드", required = true) int commentCode) throws Exception {
		logger.info("deleteComment 호출 : " + commentCode);
		
		int postCode = commentService.getPostCode(commentCode); //받아오는 정보에 게시글의 postCode가 없어 getPostCode()로 찾아옴
	
		if (commentService.deleteComment(commentCode)) {
		
			//댓글 삭세하면 댓글이 달린 게시글의 post_comment 1 감소 
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("postCode", postCode);
			map.put("count", -1);
			postService.updateComment(map);
		
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	
}
