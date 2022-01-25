package com.ssafy.newbit.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.newbit.model.CommentDto;
import com.ssafy.newbit.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private SqlSession sqlSession;
	

	@Override
	public boolean writeComment(CommentDto commentDto) throws Exception {
		// TODO Auto-generated method stubs
		System.out.println(commentDto.getPostCode());
		return sqlSession.getMapper(CommentMapper.class).writeComment(commentDto) == 1;
	}

	@Override
	public List<CommentDto> listComment(int postCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CommentMapper.class).listComment(postCode);
	}

	@Override
	public boolean deleteComment(int commentCode) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(CommentMapper.class).deleteComment(commentCode) == 1;
	}

}
