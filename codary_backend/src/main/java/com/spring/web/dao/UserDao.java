package com.spring.web.dao;

import org.springframework.stereotype.Repository;

import com.spring.web.dto.UserDto;

@Repository
public interface UserDao {

	public UserDto findById(String uid);
	public void save(UserDto user);
		
	
}
