package com.ssafy.newbit.model.service;

import java.util.*;


import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;

public interface PostService {
	public boolean writePost(PostDto postDto) throws Exception;
	public List<PostDto> listPost(HashMap<String, Object> map) throws Exception;
	public List<PostDto> listUserPost(HashMap<String, Object> map) throws Exception;
	public boolean editPost(PostTextDto postTextDto) throws Exception;
	public boolean deletePost(int postCode) throws Exception;
	public PostDto getPost(int postCode) throws Exception;
	
	public void updateScrap(HashMap<String, Integer> map) throws Exception;
	public void updateLike(HashMap<String, Integer> map) throws Exception;
	public void updateComment(HashMap<String, Integer> map) throws Exception;
	
	public List<Integer> getFollowingList(int userCode) throws Exception;
	
	public boolean likePost(HashMap<String, Integer> map) throws Exception;
	public boolean scrapPost(HashMap<String, Integer> map) throws Exception;
	
	public boolean deleteLikePost(HashMap<String, Integer> map) throws Exception;
	public boolean deleteScrapPost(HashMap<String, Integer> map) throws Exception;
	
	public boolean userLikePost(HashMap<String, Object> map) throws Exception;
	public boolean userScrapPost(HashMap<String, Object> map) throws Exception;
}
