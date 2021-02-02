package com.spring.web.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
public class UserServiceImpl implements UserService {

	@Autowired
	SqlSession sqlSession;

	@Override
	public UserDto findByProvider(HashMap<String, Object> userInfo) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("provider", (String) userInfo.get("provider"));
		map.put("providerId", (String) userInfo.get("providerId"));

		return sqlSession.getMapper(UserDao.class).findByProvider(map);
	}

	@Override
	public UserDto findById(String uid) throws Exception {
		return sqlSession.getMapper(UserDao.class).findById(uid);
	}

	@Override
	public UserInfoDto findUserInfoById(String uid) throws SQLException {
		return sqlSession.getMapper(UserDao.class).findUserInfoById(uid);
	}

	@Override
	public BlogDto findBlogById(String blogId) throws SQLException {
		return sqlSession.getMapper(UserDao.class).findBlogById(blogId);
	}

	@Override
	public Map<String, Object> save(HashMap<String, Object> userInfo) throws Exception {

		final String BLOG_DEFAULT_IMG = "##### DEFAULT_IMAGE ####";

		String uid = makeUid();
		String blogId = makeBlogId();
		String nickname = (String) userInfo.get("nickname");
		String username = nickname;
		String provider = (String) userInfo.get("provider");
		String providerId = (String) userInfo.get("providerId");
		String profileImg = (String) userInfo.get("profileImg");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		UserDto user = new UserDto(uid, blogId, uid, provider, providerId);
		UserInfoDto info = new UserInfoDto(uid, nickname, username, profileImg);
		BlogDto blog = new BlogDto(blogId, 0, nickname, BLOG_DEFAULT_IMG, 0);

		resultMap.put("userId", user.getUid());
		resultMap.put("userInfo", info);
		resultMap.put("blogId", user.getBlogId());
		resultMap.put("memoId", user.getMemoId());
		

		// 3. blog 테이블 생성
		sqlSession.getMapper(UserDao.class).makeBlog(blog);
				
		// 4. memo 테이블 생성
		sqlSession.getMapper(UserDao.class).makeMemo(new MemoDto(uid));
		
		// 1. user 테이블 생성
		sqlSession.getMapper(UserDao.class).save(user);

		// 2. userinfo 테이블 생성
		sqlSession.getMapper(UserDao.class).saveUserInfo(info);

		return resultMap;
	}

	public String makeUid() throws Exception {
		UserDto user = null;
		StringBuilder sb = new StringBuilder();
		while (true) {
			String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

			for (int i = 0; i < 12; i++) {
				sb.append(str.charAt((int) (Math.random() * str.length())));
			}
			user = findById(sb.toString());
			if (user == null)
				break;
		}
		return sb.toString();
	}

	public String makeBlogId() throws Exception {
		BlogDto blog = null;
		StringBuilder sb = new StringBuilder();
		while (true) {
			String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

			for (int i = 0; i < 12; i++) {
				sb.append(str.charAt((int) (Math.random() * str.length())));
			}
			blog = findBlogById(sb.toString());
			if (blog == null)
				break;
		}
		return sb.toString();
	}

}
