package com.spring.web.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.web.dto.BlogDto;
import com.spring.web.dto.MemoDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

@Repository
public interface UserDao {

	public UserDto findByProvider(Map<String, String> map) throws SQLException;
	public UserDto findById(String uid) throws SQLException;
	public UserInfoDto findUserInfoById(String uid) throws SQLException;
	public BlogDto findBlogById(String blogId) throws SQLException;
	public void save(UserDto user) throws SQLException;
	public void saveUserInfo(UserInfoDto userInfo) throws SQLException;
	public void makeBlog(BlogDto blog) throws SQLException;
	public void makeMemo(MemoDto memo) throws SQLException;
	public void updateNickname(Map<String, String> map) throws SQLException;
	public void deleteUser(String uid) throws SQLException;
	public void deleteBlog(String blogId) throws SQLException;
	public void deleteBlogContents(String blogId) throws SQLException;
	public void deleteComment(String blogId) throws SQLException;
	

}
