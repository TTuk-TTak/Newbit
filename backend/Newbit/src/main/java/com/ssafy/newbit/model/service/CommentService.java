package com.ssafy.newbit.model.service;
import java.util.*;

import com.ssafy.newbit.model.CommentDto;

public interface CommentService {

	public boolean writeComment(CommentDto commentDto) throws Exception;
	public List<CommentDto> listComment(int postCode)throws Exception;
	public boolean deleteComment(int commentCode) throws Exception;
	public int getPostCode(int commentCode) throws Exception;
}
