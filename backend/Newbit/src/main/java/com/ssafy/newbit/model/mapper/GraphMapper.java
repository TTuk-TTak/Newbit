package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ssafy.newbit.model.DailyDataDto;
import com.ssafy.newbit.model.TechblogDto;

public interface GraphMapper {
	
	public List<String> getKeyword(int uid) throws SQLException;
	public List<DailyDataDto> getDailyData(HashMap<String, Object> map) throws SQLException;
}
