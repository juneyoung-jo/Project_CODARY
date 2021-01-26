package com.spring.web.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.UserDao;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.MemoDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public UserDto findByProvider(HashMap<String, Object> userInfo) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("provider", (String)userInfo.get("provider"));
		map.put("providerId", (String)userInfo.get("providerId"));
		
		return sqlSession.getMapper(UserDao.class).findByProvider(map);
	}

	
	@Override
	public UserDto findById(String uid) {
		return sqlSession.getMapper(UserDao.class).findById(uid);
	}

	@Override
	public void save(HashMap<String, Object> userInfo) {
		for(int i=0; i<10; i++) {
			System.out.println(makeUid());
		}
		
		String uid = makeUid();
		String blogId = uid;
		String nickname = (String) userInfo.get("nickname");
		String provider = (String) userInfo.get("provider");
		String providerId = (String) userInfo.get("providerId");
		String defaultImg = "default";
		
		// 1. user 테이블 생성
		sqlSession.getMapper(UserDao.class).save(new UserDto(uid, uid, uid, provider, providerId));
		
		// 2. userinfo 테이블 생성
		sqlSession.getMapper(UserDao.class).saveUserInfo(new UserInfoDto(uid, nickname, defaultImg));

		// 3. blog 테이블 생성
		sqlSession.getMapper(UserDao.class).makeBlog(new BlogDto(uid, 0, nickname, defaultImg, 0));
		
		// 4. memo 테이블 생성
		sqlSession.getMapper(UserDao.class).makeMemo(new MemoDto(uid));
		
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
