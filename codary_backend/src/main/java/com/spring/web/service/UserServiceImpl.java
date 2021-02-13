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
import com.spring.web.dto.LoginCallBackDto;
import com.spring.web.dto.MemoDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	SqlSession sqlSession;

	/**
	 * Provider 정보를 이용한 UserDto값 읽어오기
	 * */
	@Override
	public UserDto findByProvider(HashMap<String, Object> userInfo) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("provider", (String) userInfo.get("provider"));
		map.put("providerId", (String) userInfo.get("providerId"));

		return sqlSession.getMapper(UserDao.class).findByProvider(map);
	}

	/**
	 * Uid 정보를 이용한 UserDto값 읽어오기
	 * */
	@Override
	public UserDto findById(String uid) throws Exception {
		return sqlSession.getMapper(UserDao.class).findById(uid);
	}

	/**
	 * Provider 정보를 이용한 UserInfoDto값 읽어오기
	 * */
	@Override
	public UserInfoDto findUserInfoById(String uid) throws SQLException {
		return sqlSession.getMapper(UserDao.class).findUserInfoById(uid);
	}

	/**
	 * blogId 정보를 이용한 BlogDto값 읽어오기
	 * */
	@Override
	public BlogDto findBlogById(String blogId) throws SQLException {
		return sqlSession.getMapper(UserDao.class).findBlogById(blogId);
	}

	/**
	 * UserDto, UserInfo, BlogDto, MemoDto 정보 삽입 후
	 * LoginCallBackDto 객체 반환
	 * */
	@Override
	public LoginCallBackDto save(HashMap<String, Object> userInfo) throws Exception {

		final String BLOG_DEFAULT_IMG = "https://codaryproject.s3.ap-northeast-2.amazonaws.com/50c5c53450fd40f79b966e16971fcbb2.jpg";

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
		LoginCallBackDto loginCallBackDto = new LoginCallBackDto();
		
		loginCallBackDto.setUid(uid);
		loginCallBackDto.setBlogId(blogId);
		loginCallBackDto.setMemoId(uid);
		loginCallBackDto.setNickname(nickname);
		loginCallBackDto.setProfile(profileImg);
		loginCallBackDto.setProvider(provider);

		// 3. blog 테이블 생성
		sqlSession.getMapper(UserDao.class).makeBlog(blog);
				
		// 4. memo 테이블 생성
		sqlSession.getMapper(UserDao.class).makeMemo(new MemoDto(uid));
		
		// 1. user 테이블 생성
		sqlSession.getMapper(UserDao.class).save(user);

		// 2. userinfo 테이블 생성
		sqlSession.getMapper(UserDao.class).saveUserInfo(info);

		return loginCallBackDto;
	}

	/**
	 * 13자리 난수값 생성 Uid 중복체크
	 * */
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

	/**
	 * 13자리 난수값 생성 BlogId 중복체크
	 * */
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

	/**
	 * UserInfo 닉네임 업데이트
	 * */
	@Override
	public void updateUserInfo(UserInfoDto info) throws Exception {
		sqlSession.getMapper(UserDao.class).updateUserInfo(info);
		return;
	}
	/**
	 * User isdeleted 값 1로 갱신
	 * Blog isdeleted 값 1로 갱신
	 * BlogContents isdeleted 값 1로 갱신
	 * Comment isdeleted 값 1로 갱신
	 * */
	@Override
	public void delete(String uid, String blogId) throws Exception {
		sqlSession.getMapper(UserDao.class).deleteUser(uid);
		sqlSession.getMapper(UserDao.class).deleteBlog(blogId);
		sqlSession.getMapper(UserDao.class).deleteBlogContents(blogId);
		sqlSession.getMapper(UserDao.class).deleteComment(blogId);
		
	}


}
