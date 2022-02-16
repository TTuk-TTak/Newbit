package com.ssafy.newbit.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.newbit.model.DailyDataDto;
import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;
import com.ssafy.newbit.model.mapper.GraphMapper;
import com.ssafy.newbit.model.mapper.InfoMapper;
import com.ssafy.newbit.model.mapper.PostMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int getMembers() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoMapper.class).getMembers();
	}

	@Override
	public int getContents() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoMapper.class).getContents();
	}

	@Override
	public int getPosts() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(InfoMapper.class).getPosts();
	}

	
	

}
