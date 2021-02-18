package com.spring.web.service;

import java.util.Map;

import com.spring.web.dto.BlogDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

public interface JwtService {

	<T> String create(String key, T data, String subject);
	
	Map<String, Object> get(String key);
	
	String getUserId(String jwt);

	boolean isUsable(String jwt);

}
