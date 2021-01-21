package com.spring.web.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.dao.UserDao;
import com.spring.web.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	SqlSession SqlSession;
	
	@Override
	public UserDto login() {
		return SqlSession.getMapper(UserDao.class).login();
	}

}
