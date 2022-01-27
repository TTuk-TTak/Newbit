package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;
	
@Mapper
public interface PostMapper {
	public int writePost(PostDto postDto) throws SQLException;
<<<<<<< HEAD
	public List<PostDto> listPost(int userCode) throws SQLException;
	public List<PostDto> listUserPost(int userCode) throws SQLException;
=======
	public List<PostDto> listPost(HashMap<String, Object> map) throws SQLException;
	public List<PostDto> listUserPost(HashMap<String, Object> map) throws SQLException;
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
	public int editPost(PostTextDto postTextDto) throws SQLException;
	public int deletePost(int postCode) throws SQLException;
	public PostDto getPost(int postCode) throws SQLException;
	
	public void updateScrap(int postCode) throws SQLException;
	public void updateLike(int postCode) throws SQLException;
	public void updateComment(HashMap<String, Integer> map) throws SQLException;
<<<<<<< HEAD
=======
	
	public List<Integer> getFollowingList(int userCode) throws SQLException;
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
}
