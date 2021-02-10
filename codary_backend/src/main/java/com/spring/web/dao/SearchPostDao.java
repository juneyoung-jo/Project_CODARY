package com.spring.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogPostDto;

@Mapper
public interface SearchPostDao {
//	public List<BlogPostDto> searchPost(SearchParam param) throws SQLException;
	List<BlogPostDto> searchTitle() throws SQLException;
	List<BlogPostDto> searchHash(String keyword) throws SQLException;
	List<Integer> searchByHash(Map<String, Object> map) throws SQLException;
	List<Map<String, Object>> searchByTitle() throws SQLException;
}
