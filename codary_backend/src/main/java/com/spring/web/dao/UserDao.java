package com.spring.web.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.web.dto.BlogDto;
import com.spring.web.dto.MemoDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

@Repository
public interface UserDao {

	public UserDto findByProvider(Map<String, String> map);
	public UserDto findById(String uid);
	public BlogDto findByBlogId(String blogId);
	public void save(UserDto user);
	public void saveUserInfo(UserInfoDto userInfo);
	public void makeBlog(BlogDto blog);
	public void makeMemo(MemoDto memo);
		
	
}
