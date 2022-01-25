package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.UserDto;

@Mapper
public interface UserMapper {

	public int editUserInfo(UserDto userDto) throws SQLException;
	public UserDto getUser(int userCode) throws SQLException;
	List<UserDto> getFollowingList(int userCode) throws SQLException;
	List<UserDto> getFollowerList(int userCode) throws SQLException;

}
