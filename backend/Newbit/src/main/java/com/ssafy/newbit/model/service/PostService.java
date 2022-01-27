package com.ssafy.newbit.model.service;

import java.util.*;


import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;

public interface PostService {
	public boolean writePost(PostDto postDto) throws Exception;
<<<<<<< HEAD
	public List<PostDto> listPost(int userCode) throws Exception;
	public List<PostDto> listUserPost(int userCode) throws Exception;
=======
	public List<PostDto> listPost(HashMap<String, Object> map) throws Exception;
	public List<PostDto> listUserPost(HashMap<String, Object> map) throws Exception;
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
	public boolean editPost(PostTextDto postTextDto) throws Exception;
	public boolean deletePost(int postCode) throws Exception;
	public PostDto getPost(int postCode) throws Exception;
	
	public void updateScrap(int postCode) throws Exception;
	public void updateLike(int postCode) throws Exception;
	public void updateComment(HashMap<String, Integer> map) throws Exception;
<<<<<<< HEAD
=======
	
	public List<Integer> getFollowingList(int userCode) throws Exception;
	
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
}
