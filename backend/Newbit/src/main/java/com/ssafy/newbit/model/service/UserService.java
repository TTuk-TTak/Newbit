package com.ssafy.newbit.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.newbit.model.UserDto;

public interface UserService {

	public boolean editUserInfo(UserDto memberDto) throws Exception;

	public UserDto getUser(int userCode) throws Exception;
	
}
