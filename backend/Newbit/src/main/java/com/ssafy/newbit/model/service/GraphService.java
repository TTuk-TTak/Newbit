package com.ssafy.newbit.model.service;

import java.util.*;

import com.ssafy.newbit.model.DailyDataDto;


public interface GraphService {
	public List<String> getKeyword(int uid) throws Exception;
	public List<DailyDataDto> getDailyData(HashMap<String, Object> map) throws Exception;
}
