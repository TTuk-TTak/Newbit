package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.CommentDto;

public interface CommentMapper {
	
	public int writeComment(CommentDto commentDto) throws SQLException;
	public List<CommentDto> listComment(int postCode) throws SQLException;
	public int deleteComment(int commentCode) throws SQLException;
	public int getPostCode(int commentCode) throws SQLException;
	
}
