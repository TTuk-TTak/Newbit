package com.ssafy.newbit.model.mapper;
import java.sql.SQLException;
import java.util.*;


import com.ssafy.newbit.model.ContentDto;
public interface ContentMapper {
	public  List<ContentDto> newListContent(HashMap<String, Object> map) throws SQLException;
	public ContentDto getContent(int contentCode) throws SQLException;
}
