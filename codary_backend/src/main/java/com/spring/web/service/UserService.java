package com.spring.web.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.spring.web.dto.BlogDto;
import com.spring.web.dto.LoginCallBackDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

public interface UserService {

	public UserDto findByProvider(HashMap<String, Object> userInfo) throws Exception;

	public UserDto findById(String uid) throws Exception;

	public UserInfoDto findUserInfoById(String uid) throws SQLException;

	public BlogDto findBlogById(String blogId) throws SQLException;

	public LoginCallBackDto save(HashMap<String, Object> userInfo) throws Exception;
	
	public void updateUserInfo(UserInfoDto info) throws Exception;

	public void delete(String uid, String blogId) throws Exception;
	
}
