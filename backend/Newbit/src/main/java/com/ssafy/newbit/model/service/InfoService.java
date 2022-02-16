package com.ssafy.newbit.model.service;

import java.sql.SQLException;
import java.util.*;

//import com.ssafy.newbit.controller.UserDetail;
import com.ssafy.newbit.model.UserDto;



public interface InfoService{
	public int getMembers() throws Exception;
	public int getContents() throws Exception;
	public int getPosts() throws Exception;
}
