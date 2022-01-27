package com.ssafy.newbit.model.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.newbit.model.PostDto;
import com.ssafy.newbit.model.PostTextDto;
import com.ssafy.newbit.model.mapper.PostMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writePost(PostDto postDto) throws Exception {
		// TODO Auto-generated method stub
		if(postDto.getPostText() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(PostMapper.class).writePost(postDto) == 1;
	}

	@Override
<<<<<<< HEAD
	public List<PostDto> listPost(int userCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(PostMapper.class).listPost(userCode);
=======
	public List<PostDto> listPost(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(PostMapper.class).listPost(map);
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
	}

	@Override
	public boolean editPost(PostTextDto postTextDto) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(PostMapper.class).editPost(postTextDto) == 1;
	}

	@Override
	public boolean deletePost(int postCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(PostMapper.class).deletePost(postCode) == 1;
	}

	@Override
	public PostDto getPost(int postCode) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("getpost 호출 : " + postCode);
		PostDto post = sqlSession.getMapper(PostMapper.class).getPost(postCode);
		if(post ==null)
			System.out.println("null");
		return post;
	}

	@Override
<<<<<<< HEAD
	public List<PostDto> listUserPost(int userCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(PostMapper.class).listUserPost(userCode);
=======
	public List<PostDto> listUserPost(HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(PostMapper.class).listUserPost(map);
>>>>>>> feat/#S06P12A101-43/F06-2/post_list
	}

	@Override
	public void updateScrap(int postCode) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.getMapper(PostMapper.class).updateScrap(postCode);
	}

	@Override
	public void updateLike(int postCode) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.getMapper(PostMapper.class).updateLike(postCode);
	}

	@Override
	public void updateComment(HashMap<String, Integer> map) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(map.get("count"));
		System.out.println(map.get("postCode"));
		sqlSession.getMapper(PostMapper.class).updateComment(map);
		
	}

<<<<<<< HEAD
=======
	@Override
	public List<Integer> getFollowingList(int userCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(PostMapper.class).getFollowingList(userCode);
	}

>>>>>>> feat/#S06P12A101-43/F06-2/post_list
	

}
