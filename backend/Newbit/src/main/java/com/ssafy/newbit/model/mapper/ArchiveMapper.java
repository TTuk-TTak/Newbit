package com.ssafy.newbit.model.mapper;
import java.sql.SQLException;
import java.util.*;


import com.ssafy.newbit.model.ContentDto;
import com.ssafy.newbit.model.PostDto;
public interface ArchiveMapper {
	public  List<ContentDto> archiveListContent(HashMap<String, Object> map) throws SQLException;
	public  List<PostDto> archiveListPost(HashMap<String, Object> map) throws SQLException;


	public List<Integer> getPostScrapList(int userCode) throws SQLException;
	public List<Integer> getContentScrapList(int userCode) throws SQLException;
	}
