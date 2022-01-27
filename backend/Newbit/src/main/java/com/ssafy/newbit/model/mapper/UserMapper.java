package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.UserDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//여기서 부터 내가 추가한거
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
	public UserDto getUser(int userCode) throws SQLException;
	public int checkId(String userId) throws Exception;
	public int checkEmail(String userEmail) throws Exception;
	public static final PasswordEncoder passwordEncoder = null;
	

}
