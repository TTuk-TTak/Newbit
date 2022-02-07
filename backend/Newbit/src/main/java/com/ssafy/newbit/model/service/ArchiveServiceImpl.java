package com.ssafy.newbit.model.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.newbit.model.ContentDto;
import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.mapper.ArchiveMapper;
import com.ssafy.newbit.model.mapper.ContentMapper;

@Service
public class ArchiveServiceImpl implements ArchiveService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PostDto> listPost(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ArchiveMapper.class).archiveListPost(map);
	}

	@Override
	public List<ContentDto> listContent(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ArchiveMapper.class).archiveListContent(map);
	}

	@Override
	public List<Integer> getPostScrapList(int userCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ArchiveMapper.class).getPostScrapList(userCode);
	}

	@Override
	public List<Integer> getContentScrapList(int userCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ArchiveMapper.class).getContentScrapList(userCode);
	}



}
