package com.spring.web.service;

import java.util.Map;

import com.spring.web.dto.BlogDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	
	<T> String create(UserDto user, UserInfoDto userinfo, BlogDto blog, String subject);

	Map<String, Object> get(String key);
	
	Map<String, Object> parseAccessToken(String key);

	String getUserId();

	boolean isUsable(String jwt);

}
