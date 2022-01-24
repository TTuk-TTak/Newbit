package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.UserDto;

@Mapper
public interface UserMapper {

	public int editUserInfo(UserDto userDto) throws SQLException;
	
	
}
