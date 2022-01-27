package com.ssafy.newbit.model.service;

import java.sql.SQLException;
import java.util.HashMap;

import com.ssafy.newbit.model.ContentDto;

public interface ContentService {
	public boolean newListContent(HashMap<String, Object> map) throws Exception;
	public ContentDto getContent(int contentCode) throws Exception;

}
