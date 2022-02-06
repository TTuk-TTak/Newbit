package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
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
	public String checkLogin(String userEmail) throws SQLException;
	
	public int editUserInfo(UserDto userDto) throws SQLException;
	public UserDto getUser(int userCode) throws SQLException;
	List<UserDto> getFollowingList(int userCode) throws SQLException;
	List<UserDto> getFollowerList(int userCode) throws SQLException;
	
}
