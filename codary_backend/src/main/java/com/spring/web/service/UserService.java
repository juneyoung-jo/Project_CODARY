package com.spring.web.service;

import java.util.HashMap;

import com.spring.web.dto.BlogDto;
import com.spring.web.dto.UserDto;

public interface UserService {

	public UserDto findByProvider(HashMap<String, Object> userInfo) throws Exception;
	public UserDto findById(String uid) throws Exception;
	public BlogDto findByBlogId(String blogId) throws Exception;
	public UserDto save(HashMap<String, Object> userInfo) throws Exception;
}
