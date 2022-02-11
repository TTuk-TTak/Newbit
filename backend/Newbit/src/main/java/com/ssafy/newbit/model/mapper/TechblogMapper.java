package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;

import com.ssafy.newbit.model.TechblogDto;

public interface TechblogMapper {
	
	public TechblogDto getTechblogInfo(int techblogcode) throws SQLException;
}
