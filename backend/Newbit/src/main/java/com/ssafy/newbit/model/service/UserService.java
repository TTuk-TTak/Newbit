package com.ssafy.newbit.model.service;

import java.sql.SQLException;
import java.util.*;

import com.ssafy.newbit.model.UserDto;



public interface UserService {
	public boolean addUser(UserDto userDto) throws Exception;
	public UserDto getUser(int userCode) throws Exception;
	public boolean checkId(String userId) throws Exception;
	public boolean checkEmail(String userEmail) throws Exception;

}
