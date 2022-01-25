package com.ssafy.newbit.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newbit.model.UserDto;
import com.ssafy.newbit.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	@Transactional
	public boolean editUserInfo(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).editUserInfo(userDto) == 1;
	}

	@Override
	public UserDto getUser(int userCode) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getUser(userCode);
		
	}
}
