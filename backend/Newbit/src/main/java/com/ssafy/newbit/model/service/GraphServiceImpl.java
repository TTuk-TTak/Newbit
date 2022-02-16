package com.ssafy.newbit.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;
import com.ssafy.newbit.model.mapper.GraphMapper;
import com.ssafy.newbit.model.mapper.PostMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GraphServiceImpl implements GraphService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<String> getKeyword(int uid) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(GraphMapper.class).getKeyword(uid);
	}
	
	
	

}
