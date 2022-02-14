package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.newbit.model.TechblogDto;

public interface GraphMapper {
	
	public List<String> getKeyword(int uid) throws SQLException;
}
