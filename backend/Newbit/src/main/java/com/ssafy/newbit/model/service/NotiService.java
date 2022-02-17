package com.ssafy.newbit.model.service;

import java.util.*;

import com.ssafy.newbit.model.NotiDto;

public interface NotiService {
	public List<NotiDto> getNoti(int userCode) throws Exception;
}
