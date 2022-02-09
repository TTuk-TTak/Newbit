package com.ssafy.newbit.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.mapper.PostMapper;
import com.ssafy.newbit.model.mapper.UserMapper;

//for Password Hashing
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private SqlSession sqlSession;
	
	//for Password Hashing
	@Autowired UserMapper userMapper;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	///////////////////////////    회원 가입  관련     //////////////////////////////////////////////
	// 회원가입
	@Override
	public boolean addUser(UserDto userDto) throws Exception {
		// 아이디, 이메일 중복체크 후 회원가입 진행
		int user=0;
		int okid = sqlSession.getMapper(UserMapper.class).checkId(userDto.getUserId());
		int okemail = sqlSession.getMapper(UserMapper.class).checkEmail(userDto.getUserEmail());
		if (okid == 0 && okemail == 0) {	// 아이디, 이메일 모두 중복이 없을 경우
			// 비밀번호 암호화
	        String encodePassword = passwordEncoder.encode(userDto.getUserPassword());
	        userDto.setUserPassword(encodePassword);
	        // 회원가입실행
			user = sqlSession.getMapper(UserMapper.class).addUser(userDto);	
			System.out.println("회원가입 성공!");
		}
		else
			System.out.println("회원가입 실패!");	
		return user ==1;	// 회원가입 성공시  T,회원가입 실패시 F 반환
	}
	
	
	// 아이디 중복확인 
  @Override
  public boolean checkId(String userId) throws Exception{
    // True 반환하면 -> 아이디 사용 가능, False 반환하면 -> 아이디 사용불가
    System.out.println("id 중복여부: " + userId);
    int cnt = sqlSession.getMapper(UserMapper.class).checkId(userId);
    if(cnt == 0)
      System.out.println("사용가능 아이디");
    else
      System.out.println("사용 불가능 아이디");
      return cnt==0;
  }
    
	// 이메일 중복확인 
  @Override
  public boolean checkEmail(String userEmail) throws Exception{
    // True 반환하면 -> 이메일 사용 가능, False 반환하면 -> 이메일 사용불가
    System.out.println("email 중복여부: " + userEmail);
    int cnt = sqlSession.getMapper(UserMapper.class).checkEmail(userEmail);
    if(cnt == 0)
      System.out.println("사용가능 이메일");
    else
      System.out.println("사용 불가능 이메일");
      return cnt==0;
  }


  ///////////////////////////// 최초 로그인 시 로직 //////////////////////////////////////////////////////////
    // 유저 관심키워드 설정 
    @Override
    public boolean addUserKeyword(HashMap<String, Object> map) throws Exception{
    	return sqlSession.getMapper(UserMapper.class).addUserKeyword(map) == 1;
    }
    // 유저 자기소개 설정
    @Override
    public boolean addUserIntro(HashMap<String, Object> map) throws Exception{
    	return sqlSession.getMapper(UserMapper.class).addUserIntro(map) == 1;
    }
    
    
    ///////////////////////////    로그인     //////////////////////////////////////////////////////////
    
    // 로그인 확인
    @Override
    public String checkLogin(String userEmail, String userPassword) throws Exception{
    	System.out.println("로그인 가능여부: " + userEmail);
    	String userCode = "";
    	// db에서 가져온 encoded password
    	String pwd = sqlSession.getMapper(UserMapper.class).checkLogin(userEmail);
    	// 비밀번호 비교 TF
    	boolean TF = passwordEncoder.matches(userPassword, pwd);
    	if(TF == true) {
    		System.out.println("로그인 가능 아이디");
    		userCode = sqlSession.getMapper(UserMapper.class).getUserCode(userEmail);
    	}else{
    		System.out.println("로그인 불가능 아이디");
    	}
        return userCode;
    }
    
    
    /////////////////////////////사용자 정보 확인 /////////////////////////////////////////////////////
	// 유저 조회
	@Override
	public UserDto getUser(int userCode) throws Exception {
		System.out.println("getuser 호출 : " + userCode);
		UserDto user = sqlSession.getMapper(UserMapper.class).getUser(userCode);
		if(user ==null)
			System.out.println("null");
		return user;
	}    
    
  //팔로잉 리스트 조회
	@Override
	public List<UserDto> getFollowingList(int userCode) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getFollowingList(userCode);
	}

  //팔로워 리스트 조회
	@Override
	public List<UserDto> getFollowerList(int userCode) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getFollowerList(userCode);
    }


	/////////////////////////////사용자 정보수정 /////////////////////////////////////////////////////
	//회원정보 수정
	@Override
	@Transactional
	public boolean editUserInfo(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).editUserInfo(userDto) == 1;
	}

	//팔로잉 추가
	@Override
	public boolean followUser(HashMap<String, Integer> map) throws Exception {
		return sqlSession.getMapper(UserMapper.class).followUser(map) == 1;
	}
  
	//팔로잉 삭제
	@Override
	public boolean unfollowUser(HashMap<String, Integer> map) throws Exception {
		return sqlSession.getMapper(UserMapper.class).unfollowUser(map) == 1;
	}

	///////////////////////////   검색     //////////////////////////////////////////////////////////
	//유저 검색
	@Override
	public List<UserDto> searchUserList(HashMap<String, Object> map) throws Exception {
		return sqlSession.getMapper(UserMapper.class).searchUserList(map);
	}  
	
	
	/////////////////////////////  회원 탈퇴  /////////////////////////////////////////////////////
	@Override
	public boolean deleteUser(int userCode) throws Exception{
    	System.out.println("회원탈퇴 가능여부: " + userCode);
    	return sqlSession.getMapper(UserMapper.class).deleteUser(userCode)==1;
	}
	
	

}
