package com.ssafy.newbit.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ssafy.newbit.model.ContentDto;

public interface ContentService {
	public List<ContentDto> newListContent(HashMap<String, Object> map) throws Exception;
	public List<ContentDto> hotListContent(HashMap<String, Object> map) throws Exception;
	public ContentDto getContent(int contentCode) throws Exception;

	public void updateScrap(HashMap<String, Integer> map) throws Exception;
	public void updateLike(HashMap<String, Integer> map) throws Exception;
	public void updateRead(HashMap<String, Integer> map) throws Exception;
	
	public boolean likeContent(HashMap<String, Integer> map) throws Exception;
	public boolean scrapContent(HashMap<String, Integer> map) throws Exception;
	public boolean readContent(HashMap<String, Integer> map) throws Exception;
	
	public boolean deleteLikeContent(HashMap<String, Integer> map) throws Exception;
	public boolean deleteScrapContent(HashMap<String, Integer> map) throws Exception;
	
	public boolean userLikeContent(HashMap<String, Object> map) throws Exception;
	public boolean userScrapContent(HashMap<String, Object> map) throws Exception;

}
