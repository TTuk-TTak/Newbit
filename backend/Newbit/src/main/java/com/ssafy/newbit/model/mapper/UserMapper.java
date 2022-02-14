package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.UserDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import org.apache.ibatis.annotations.Select;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import com.ssafy.newbit.controller.UserController;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public interface UserMapper {

	public int addUser(UserDto userDto) throws SQLException;
	public int checkId(String userId) throws Exception;
	public int checkEmail(String userEmail) throws Exception;
	public static final PasswordEncoder passwordEncoder = null;
	
	public int addUserKeyword(HashMap<String, Object> map) throws SQLException;
	public int addUserIntro(HashMap<String, Object> map) throws SQLException;
	
	public String checkLogin(String userEmail) throws SQLException;
	public String getUserCode(String userEmail) throws SQLException;
	
	public UserDto getUser(int userCode) throws SQLException;
	List<UserDto> getFollowingList(int userCode) throws SQLException;
	List<UserDto> getFollowerList(int userCode) throws SQLException;
	
	public int editUserInfo(UserDto userDto) throws SQLException;
	public String matchUserId(UserDto userDto) throws SQLException;
	public int followUser(HashMap<String, Integer> map) throws SQLException;
	public int unfollowUser(HashMap<String, Integer> map) throws SQLException;
	public List<UserDto> searchUserList(HashMap<String, Object> map) throws SQLException;
	
	public int deleteUser(int userCode) throws SQLException;
	
	public List<UserDto> followRecommendation(HashMap<String, Object> map) throws SQLException;
	public List<UserDto> getRandomUser(HashMap<String, Object> map) throws SQLException;
	public List<UserDto> getFollowNoti(int userCode) throws SQLException;
}
