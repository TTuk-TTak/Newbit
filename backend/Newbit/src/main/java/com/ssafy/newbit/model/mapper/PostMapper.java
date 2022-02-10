package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;
	
@Mapper
public interface PostMapper {
	public int writePost(PostDto postDto) throws SQLException;
	public List<PostDto> listPost(HashMap<String, Object> map) throws SQLException;
	public List<PostDto> listUserPost(HashMap<String, Object> map) throws SQLException;
	public int editPost(PostTextDto postTextDto) throws SQLException;
	public int deletePost(int postCode) throws SQLException;
	public PostDto getPost(int postCode) throws SQLException;
	
	public void updateScrap(HashMap<String, Integer> map) throws SQLException;
	public void updateLike(HashMap<String, Integer> map) throws SQLException;
	public void updateComment(HashMap<String, Integer> map) throws SQLException;
	
	public List<Integer> getFollowingList(int userCode) throws SQLException;
	
	public int likePost(HashMap<String, Integer> map) throws SQLException;
	public int scrapPost(HashMap<String, Integer> map) throws SQLException;
	
	
	public int deleteLikePost(HashMap<String, Integer> map) throws SQLException;
	public int deleteScrapPost(HashMap<String, Integer> map) throws SQLException;
	
	public int userLikePost(HashMap<String, Object> map) throws SQLException;
	public int userScrapPost(HashMap<String, Object> map) throws SQLException;
	public List<PostDto> searchPostList(HashMap<String, Object> map) throws SQLException;
}
	 