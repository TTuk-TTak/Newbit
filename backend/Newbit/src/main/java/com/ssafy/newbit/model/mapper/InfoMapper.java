package com.ssafy.newbit.model.mapper;
import java.sql.SQLException;
import java.util.*;


import com.ssafy.newbit.model.ContentDto;
public interface InfoMapper {
	public int getMembers() throws SQLException;
	public int getContents() throws SQLException;
	public int getPosts() throws SQLException;
}
