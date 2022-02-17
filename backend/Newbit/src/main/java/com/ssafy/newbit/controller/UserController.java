package com.ssafy.newbit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.service.UserService;
import com.ssafy.newbit.jwt.JwtProvider;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;



@Api("유저 컨트롤러  API")
@CrossOrigin(origins = { "http://localhost:8080"})
@RequestMapping("/user")		
@RestController
public class UserController{

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;
	@Autowired
	private JwtProvider jwtProvider;

	
	///////////////////////////    회원 가입  관련     //////////////////////////////////////////////
	@ApiOperation(value = "사용자 추가", notes = "회원가입한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = UserDto.class)	
	@PostMapping("/signup")
	public ResponseEntity<String> addUser(
			@RequestBody @ApiParam(value = "유저 정보.", required = true) UserDto userDto)throws Exception{  
		logger.info("addUser호출 : " );
		if(userService.addUser(userDto)) { 
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "아이디 중복 체크", notes = "중복된 아이디가 있는지 확인한다.", response = String.class)
	@PostMapping("/signup/idCheck")
	public ResponseEntity<String> checkId(
			@RequestBody @ApiParam(value = "입력받은 사용자의 아이디", required = true) UserDto userDto)throws Exception{ 
		logger.info("checkId 호출 : " );
		String userId = userDto.getUserId();
		if (userService.checkId(userId) == true) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "이메일 중복 체크", notes = "중복된 이메일이 있는지 확인한다.", response = String.class)
	@PostMapping("/signup/emailCheck")
	public ResponseEntity<String> checkEmail(
			@RequestBody @ApiParam(value = "입력받은 사용자의 이메일", required = true) UserDto userDto)throws Exception{ 
		logger.info("checkEmail 호출 : " );
		String userEmail = userDto.getUserEmail();
		if (userService.checkEmail(userEmail) == true) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	/////////////////////////////	 최초 로그인 시 로직  	//////////////////////////////////////////////////////////
	@ApiOperation(value = "관심 키워드 추가", notes = "최초 로그인 시, 유저의 관심키워드를 입력받는다", response = String.class)	
	@PostMapping("/setting/keywordset")
	public ResponseEntity<String> addUserKeyword(
			@RequestParam("uid") @ApiParam(value = "관심키워드", required = true) int usercode, String userkeyword){  	
		logger.info("addUserKeyword 호출 : " + userkeyword);

    	HashMap<String, Object> map = new HashMap<String,Object>();
    	map.put("userCode",usercode);
    	map.put("userKeyword",userkeyword);
		
		try {
			if (userService.addUserKeyword(map)) {// userDto 자체 업데이트가 불가능해서 userService.editUserInfo 못사용 
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "자기소개 추가", notes = "최초 로그인 시, 유저의 자기소개 및 프로필 사진을 입력받는다", response = String.class)	
	@PostMapping("/setting/introset")		
	public ResponseEntity<String> addUserIntro(
			@RequestParam("uid") @ApiParam(value = "자기소개 , 프로필사진", required = true) int userCode, String userIntro, String userImg){  		
		// 사진 입력시 지정 폴더/ img 타입 변경 등 후작업 필요
		logger.info("addUserIntro 호출 : " );
		 
    	HashMap<String, Object> map = new HashMap<String,Object>();
    	map.put("userIntro",userIntro);
    	map.put("userImg",userImg);
    	map.put("userCode",userCode);
		
		try {
			if (userService.addUserIntro(map)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	///////////////////////////    로그인     //////////////////////////////////////////////////////////

	@ApiOperation(value = "로그인 확인", notes = "사용자 로그인 시도가 타당한지 확인합니다..", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> checkLogin(
			@RequestBody @ApiParam(value = "사용자의 이메일&아이디", required = true) Map<String,String> user)throws Exception{ 
		logger.info("checkLogin 호출 : ");		// "checkLogin 호출 : " + user
		Map<String, String> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String userCode = userService.checkLogin(user.get("userEmail"), user.get("userPassword"));
			if(userCode != "") {
				String token = jwtProvider.createToken(user.get("userEmail"), userCode);// key, data, subject
				
				//response.setHeader("jwt-auth-token", token); // client에 token 전달 
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("userCode", userCode);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;				
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(resultMap, status);
	
	}
	
	
	/////////////////////////////JWT 확인용 (필요없는 로직, 프로젝트 끝난 후 삭제할 것) /////////////////////////////////////////////////////

    // 접근확인
    @PostMapping("/setting/jwttest")
    public Claims Test() {
    	String token = jwtProvider.createToken("dddddd", "User");
        return jwtProvider.getJwtContents(token);
    }
    
    
    /////////////////////////////  유저 정보 + 프로필 조회   /////////////////////////////////////////////////////
    
	@ApiOperation(value = "타 유저 프로필 확인", notes = "사용자 코드에 해당하는 사용자의 정보를 반환한다.", response = UserDto.class)
	@GetMapping("")
	public ResponseEntity<UserDto> getUser(
			@RequestParam("uid") @ApiParam(value = "얻어올 사용자의 코드", required = true) int userCode)throws Exception{ 
		logger.info("getUser 호출 : " + userCode);
		return new ResponseEntity<UserDto>(userService.getUser(userCode), HttpStatus.OK);
	}

    /////////////////////////////사용자 정보수정 /////////////////////////////////////////////////////
	
	// 회원정보 수정
	@ApiOperation(value = "회원 정보 수정", notes = "수정할 회원 정보를 입력한다. DB 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping("/setting")
	public ResponseEntity<String> editUserInfo(
			@RequestBody @ApiParam(value = "수정 가능한 정보 : 아이디, 닉네임, 비밀번호, 한줄 소개, 프로필 사진, 관심 키워드", required = true) UserDto userDto) throws Exception {
		logger.info("editUserInfo - 호출");
		if (userService.editUserInfo(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	 /////////////////////////////   회원 탈퇴    /////////////////////////////////////////////////////

	@ApiOperation(value = "회원탈퇴", notes = "해당 유저정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/setting")
	public ResponseEntity<String> deleteUser(
			@RequestParam("uid") @ApiParam(value = "삭제할 유저코드", required = true) int userCode) throws Exception {  // 비밀 번호 확인  안하는 걸로!
		logger.info("deleteUser 호출 : " + userCode);									
		if (userService.deleteUser(userCode)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);	
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);

	}
	
	// 유저 검색
	@ApiOperation(value = "유저 검색", notes = "검색 키워드를 포함하는 유저 목록을 반환", response = List.class)
	@GetMapping("/search")
	public ResponseEntity<List<UserDto>> searchUserList(
			@RequestParam @ApiParam(value = "유저 목록을 가져오기 위해 필요한 정보", required = true)String search, int uid, int lastpostcode, int size) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("userCode", uid);
		map.put("lastPostCode", lastpostcode);
		map.put("size",size);
		logger.info("searchUserList 호출 : " + search);
		
		List<UserDto> list = userService.searchUserList(map);
		return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
	}
}
