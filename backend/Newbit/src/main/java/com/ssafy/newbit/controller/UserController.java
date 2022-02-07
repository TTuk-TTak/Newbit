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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.service.UserService;
import com.ssafy.newbit.jwt.JwtProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//////////////////여기서 부터 직접 추가한 import
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ssafy.newbit.model.mapper.UserMapper;




@Api("유저 컨트롤러  API")
@CrossOrigin(origins = { "*" })
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

	
	///////////////////////////    회원 가입       //////////////////////////////////////////////
	@ApiOperation(value = "사용자 추가", notes = "회원가입한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)	
	@PostMapping("/signup")
	public ResponseEntity<String> addUser(@RequestBody @ApiParam(value = "유저 정보.", required = true) UserDto userDto)throws Exception{  //@ApiParam(value = "유저 정보.", required = true)	//throws Exception
		if(userService.addUser(userDto)) { 
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "아이디 중복 체크", notes = "중복된 아이디가 있는지 확인한다.", response = UserDto.class)
	@PostMapping("/idCheck")
	public ResponseEntity<String> checkId(@RequestParam("userId") @ApiParam(value = "입력받은 사용자의 아이디", required = true) String userId)throws Exception{ 
		logger.info("checkId 호출 : " + userId);
		if (userService.checkId(userId) == true) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "이메일 중복 체크", notes = "중복된 이메일이 있는지 확인한다.", response = UserDto.class)
	@PostMapping("/emailCheck")
	public ResponseEntity<String> checkEmail(@RequestParam("userEmail") @ApiParam(value = "입력받은 사용자의 이메일", required = true) String userEmail)throws Exception{ 
		logger.info("checkEmail 호출 : " + userEmail);
		if (userService.checkEmail(userEmail) == true) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	///////////////////////////// 최초 로그인 시 로직 //////////////////////////////////////////////////////////
	
	@ApiOperation(value = "관심 키워드 추가", notes = "최초 로그인 시, 유저의 관심키워드를 입력받는다", response = String.class)	
	@PostMapping("/signup/keywordSet")
	public ResponseEntity<String> addUserKeyword(@RequestParam @ApiParam(value = "헤더의 유저정보", required = true) String userKeyword, HttpServletRequest request){  		
		logger.info("addUserKeyword 호출 : " + userKeyword);
		// 헤더에 포함된 jwt 토큰 디코딩  → 사용자 인증 (with userEmail)
		String token = jwtProvider.resolveToken((HttpServletRequest) request);
		String userEmail = jwtProvider.getJwtContents(token).getSubject();
		
		try {
			if (userService.addUserKeyword(userEmail, userKeyword)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	///////////////////////////    로그인       //////////////////////////////////////////////

	@ApiOperation(value = "로그인 확인", notes = "사용자 로그인 시도가 타당한지 확인합니다..", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> checkLogin(@RequestBody @ApiParam(value = "사용자의 이메일&아이디", required = true) Map<String,String> user)throws Exception{ 
		logger.info("checkLogin 호출 : ");		// "checkLogin 호출 : " + user
		Map<String, String> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			boolean TF = userService.checkLogin(user.get("userEmail"), user.get("userPassword"));
			if(TF == true) {
				String token = jwtProvider.createToken(user.get("userEmail"), "User");// key, data, subject
				//response.setHeader("jwt-auth-token", token); // client에 token 전달 
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
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
	
	
	/////////////////////////////JWT 확인용 /////////////////////////////////////////////////////

    // 접근확인
    @PostMapping("/jwttest")
    public Map userResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result","Jwt Pass!!");
        return result;
    }

    // 접근확인
    @PostMapping("/test")
    public Claims Test() {
    	String token = jwtProvider.createToken("dddddd", "User");
        return jwtProvider.getJwtContents(token);
    }
    
    /*
	// Token 정보 추출 - payload(body) -회원정보
	public Claims getJwtContents(String jwt) {		// Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
	    Claims claims = Jwts.parser()
	        .setSigningKey(secretKey.getBytes())
	        .parseClaimsJws(jwt)
	        .getBody();	
	    return claims;
	}*/
    
    /////////////////////////////사용자 정보 확인 /////////////////////////////////////////////////////
    
	@ApiOperation(value = "특정 사용자 조회", notes = "사용자 코드에 해당하는 사용자의 정보를 반환한다.", response = UserDto.class)
	@GetMapping("")
	public ResponseEntity<UserDto> getUser(@RequestParam("uid") @ApiParam(value = "얻어올 사용자의 코드", required = true) int userCode)throws Exception{ 
		logger.info("getUser 호출 : " + userCode);
		return new ResponseEntity<UserDto>(userService.getUser(userCode), HttpStatus.OK);
	}
    
    
    /////////////////////////////사용자 정보수정 /////////////////////////////////////////////////////
	
	
	// 회원정보 수정
	@ApiOperation(value = "회원 정보 수정", notes = "수정할 회원 정보를 입력한다. DB 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PatchMapping
	public ResponseEntity<String> editUserInfo(@RequestBody @ApiParam(value = "수정 가능한 정보 : 아이디, 닉네임, 비밀번호, 한줄 소개, 프로필 사진, 관심 키워드", required = true) UserDto userDto) throws Exception {
		logger.info("editUserInfo - 호출");
		System.out.println(userService.editUserInfo(userDto));
		if (userService.editUserInfo(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
