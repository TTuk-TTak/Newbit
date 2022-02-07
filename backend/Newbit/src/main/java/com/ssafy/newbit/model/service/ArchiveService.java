package com.ssafy.newbit.model.service;

import java.util.*;

import com.ssafy.newbit.model.ContentDto;
import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;

public interface ArchiveService {
	public List<PostDto> listPost(HashMap<String, Object> map) throws Exception;
	public List<ContentDto> listContent(HashMap<String, Object> map) throws Exception;
	
	public List<Integer> getPostScrapList(int userCode) throws Exception;
	public List<Integer> getContentScrapList(int userCode) throws Exception;
}
