package com.spring.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.HashtagDto;

@Mapper
public interface SearchPostDao {
//	public List<BlogPostDto> searchPost(SearchParam param) throws SQLException;
	List<BlogPostDto> searchTitle() throws SQLException;
	List<BlogPostDto> searchHash(String keyword) throws SQLException;
	List<Integer> searchByHash(Map<String, Object> map) throws SQLException;
	List<Integer> getCommentInfo(int blogContentsId) throws SQLException;
	Map<String, String> getUserProfile(String blogId) throws SQLException;
	List<BlogPostDto> getPostInfo(List<Integer> list) throws SQLException;
	List<HashtagDto> getHashtagOfPost(int blogContentsId) throws SQLException;
//	List<Map<String, Object>> searchByTitle() throws SQLException;
}
