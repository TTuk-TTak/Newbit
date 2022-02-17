package com.ssafy.newbit.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.newbit.model.NotiDto;
import com.ssafy.newbit.model.mapper.NotiMapper;


@Service
public class NotiServiceImpl implements NotiService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NotiDto> getNoti(int userCode) throws Exception {
		return sqlSession.getMapper(NotiMapper.class).getNoti(userCode);
	}
	

}
