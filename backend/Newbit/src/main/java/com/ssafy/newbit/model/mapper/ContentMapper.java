package com.ssafy.newbit.model.mapper;
import java.sql.SQLException;
import java.util.*;


import com.ssafy.newbit.model.ContentDto;
public interface ContentMapper {
	public  List<ContentDto> listContent(HashMap<String, Object> map) throws SQLException;
	public ContentDto getContent(int contentCode) throws SQLException;
	
	public void updateScrap(HashMap<String, Integer> map) throws SQLException;
	public void updateLike(HashMap<String, Integer> map) throws SQLException;
	public void updateRead(HashMap<String, Integer> map) throws SQLException;
	
	public int likeContent(HashMap<String, Integer> map) throws SQLException;
	public int scrapContent(HashMap<String, Integer> map) throws SQLException;
	public int readContent(HashMap<String, Integer> map) throws SQLException;
	
	public int deleteLikeContent(HashMap<String, Integer> map) throws SQLException;
	public int deleteScrapContent(HashMap<String, Integer> map) throws SQLException;
	
	public int userLikeContent(HashMap<String, Object> map) throws SQLException;
	public int userScrapContent(HashMap<String, Object> map) throws SQLException;
	public int userReadContent(HashMap<String, Object> map) throws SQLException;
	public List<ContentDto> searchContentList(HashMap<String, Object> map) throws SQLException;

	public long getCursor(HashMap<String, Object> map) throws SQLException;

}
