package com.ssafy.newbit.model.service;

import java.util.*;

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
	public List<ContentDto> newListContent(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).newListContent(map);
	}

	@Override
	public ContentDto getContent(int contentCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).getContent(contentCode);
	}

}
