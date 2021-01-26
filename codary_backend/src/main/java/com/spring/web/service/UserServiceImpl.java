package com.spring.web.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.UserDao;
import com.spring.web.dto.UserDto;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	SqlSession SqlSession;
	
	@Override
	public UserDto findById(String uid) {
		return SqlSession.getMapper(UserDao.class).findById(uid);
	}

	@Override
	public void save(HashMap<String, Object> userInfo) {
		String uid = makeUid();
		String blogId = uid;
		String provider = (String) userInfo.get("provider");
		String providerId = (String) userInfo.get("providerId");
		
		// 1. blog 테이블 생성
		// 2. user 테이블 생성
		// 3. userinfo 테이블 생성
//		UserDto user = new UserDto(uid, blogId, memoId, provider, providerId);
	}
	

	public String makeUid() {
		UserDto user = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
			
			for(int i=0; i<15; i++) {
				sb.append(str.charAt((int)(Math.random() * str.length())));
			}
			user = findById(sb.toString());
			if(user == null) break;
		}
		return sb.toString();
	}



	


}
