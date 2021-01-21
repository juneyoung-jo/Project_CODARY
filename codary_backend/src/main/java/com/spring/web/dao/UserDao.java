package com.spring.web.dao;

import org.springframework.stereotype.Repository;

import com.spring.web.dto.User;

@Repository
public interface UserDao {

	public User login();
		
	
}
