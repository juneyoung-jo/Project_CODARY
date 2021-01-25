package com.spring.web.service;

import com.spring.web.dto.UserDto;

public interface UserService {

	public UserDto findById(String uid); 
	
}
