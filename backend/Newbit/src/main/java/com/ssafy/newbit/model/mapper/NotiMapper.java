package com.ssafy.newbit.model.mapper;

import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.newbit.model.NotiDto;

@Mapper
public interface NotiMapper {
	public List<NotiDto> getNoti(int userCode) throws SQLException;
}
