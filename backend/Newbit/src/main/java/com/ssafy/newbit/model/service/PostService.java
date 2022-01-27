package com.ssafy.newbit.model.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;

public interface PostService {
	public boolean writePost(PostDto postDto) throws Exception;
	public List<PostDto> listPost(int userCode) throws Exception;
	public List<PostDto> listUserPost(int userCode) throws Exception;
	public boolean editPost(PostTextDto postTextDto) throws Exception;
	public boolean deletePost(int postCode) throws Exception;
	public PostDto getPost(int postCode) throws Exception;
	
	public void updateScrap(int postCode) throws Exception;
	public void updateLike(int postCode) throws Exception;
	public void updateComment(int postCode) throws Exception;
}
