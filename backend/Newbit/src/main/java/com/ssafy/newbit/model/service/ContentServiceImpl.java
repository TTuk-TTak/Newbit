package com.ssafy.newbit.model.service;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.newbit.model.ContentDto;
import com.ssafy.newbit.model.mapper.ContentMapper;
import com.ssafy.newbit.model.mapper.PostMapper;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ContentDto> listContent(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).listContent(map);
	}

	@Override
	public ContentDto getContent(int contentCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).getContent(contentCode);
	}

	@Override
	public void updateScrap(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.getMapper(ContentMapper.class).updateScrap(map);
	}

	@Override
	public void updateLike(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.getMapper(ContentMapper.class).updateLike(map);
	}

	@Override
	public boolean likeContent(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).likeContent(map) == 1;
	}

	@Override
	public boolean scrapContent(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).scrapContent(map) == 1;
	}

	@Override
	public boolean deleteLikeContent(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).deleteLikeContent(map) == 1;
	}

	@Override
	public boolean deleteScrapContent(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).deleteScrapContent(map) == 1;
	}

	@Override
	public boolean userLikeContent(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).userLikeContent(map) ==1;
	}

	@Override
	public boolean userScrapContent(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).userScrapContent(map)==1;
	}

	@Override
	public void updateRead(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.getMapper(ContentMapper.class).updateRead(map);
	}

	@Override
	public boolean readContent(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).readContent(map)==1;
	}

	// 콘텐츠 검색
	@Override
	public List<ContentDto> searchContentList(HashMap<String, Object> map) throws Exception {
		return sqlSession.getMapper(ContentMapper.class).searchContentList(map);
	}

	@Override
	public long getCursor(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ContentMapper.class).getCursor(map);
	}

}
