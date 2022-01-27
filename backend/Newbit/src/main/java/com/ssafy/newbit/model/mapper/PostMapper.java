package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;
	
@Mapper
public interface PostMapper {
	public int writePost(PostDto postDto) throws SQLException;
	public List<PostDto> listPost(int userCode) throws SQLException;
	public List<PostDto> listUserPost(int userCode) throws SQLException;
	public int editPost(PostTextDto postTextDto) throws SQLException;
	public int deletePost(int postCode) throws SQLException;
	public PostDto getPost(int postCode) throws SQLException;
	
	public void updateScrap(int postCode) throws SQLException;
	public void updateLike(int postCode) throws SQLException;
	public void updateComment(HashMap<String, Integer> map) throws SQLException;
}
