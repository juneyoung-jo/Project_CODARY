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
	public UserDto findByProvider(HashMap<String, Object> userInfo)  throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("provider", (String)userInfo.get("provider"));
		map.put("providerId", (String)userInfo.get("providerId"));
		
		return sqlSession.getMapper(UserDao.class).findByProvider(map);
	}

	
	@Override
	public UserDto findById(String uid) throws Exception {
		return sqlSession.getMapper(UserDao.class).findById(uid);
	}
	
	@Override
	public BlogDto findByBlogId(String blogId) throws Exception {
		return sqlSession.getMapper(UserDao.class).findByBlogId(blogId);
	}

	@Override
	public UserDto save(HashMap<String, Object> userInfo) throws Exception {
		
		String uid = makeUid();
		String blogId = makeBlogId();
		String nickname = (String) userInfo.get("nickname");
		String provider = (String) userInfo.get("provider");
		String providerId = (String) userInfo.get("providerId");
		String profileImg = (String) userInfo.get("profileImg");
		String blogProfile = "blogProfileDefault";
		
		// 1. user 테이블 생성
		sqlSession.getMapper(UserDao.class).save(new UserDto(uid, blogId, uid, provider, providerId));
		
		// 2. userinfo 테이블 생성
		sqlSession.getMapper(UserDao.class).saveUserInfo(new UserInfoDto(uid, nickname, profileImg));

		// 3. blog 테이블 생성
		sqlSession.getMapper(UserDao.class).makeBlog(new BlogDto(blogId, 0, nickname, blogProfile, 0));
		
		// 4. memo 테이블 생성
		sqlSession.getMapper(UserDao.class).makeMemo(new MemoDto(uid));

		return new UserDto(uid, uid, uid, provider, providerId);
	}
	

	public String makeUid() throws Exception {
		UserDto user = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
			
			for(int i=0; i<12; i++) {
				sb.append(str.charAt((int)(Math.random() * str.length())));
			}
			user = findById(sb.toString());
			if(user == null) break;
		}
		return sb.toString();
	}
	
	public String makeBlogId() throws Exception {
		BlogDto blog = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			String str ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
			
			for(int i=0; i<12; i++) {
				sb.append(str.charAt((int)(Math.random() * str.length())));
			}
			blog = findByBlogId(sb.toString());
			if(blog == null) break;
		}
		return sb.toString();
	}


	




	


}
