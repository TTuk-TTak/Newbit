package com.ssafy.newbit.model.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.newbit.model.ContentDto;
import com.ssafy.newbit.model.mapper.ContentMapper;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean newListContent(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).newListContent(map) ==1;
	}

	@Override
	public ContentDto getContent(int contentCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).getContent(contentCode);
	}

}
