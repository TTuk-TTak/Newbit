package com.ssafy.newbit.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ssafy.newbit.model.ContentDto;

public interface ContentService {
	public List<ContentDto> newListContent(HashMap<String, Object> map) throws Exception;
	public ContentDto getContent(int contentCode) throws Exception;

}
