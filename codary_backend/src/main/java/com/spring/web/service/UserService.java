package com.spring.web.service;

import java.util.HashMap;

import com.spring.web.dto.UserDto;

public interface UserService {

	public UserDto findByProvider(HashMap<String, Object> userInfo); 
	public UserDto findById(String uid); 
	public void save(HashMap<String, Object> userInfo);
}
