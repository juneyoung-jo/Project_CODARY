package com.spring.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.SearchParam;

@Repository
public interface SearchPostDao {
	public List<BlogPostDto> searchPost(SearchParam param) throws SQLException;
	public List<BlogPostDto> searchPostByTag(SearchParam param) throws SQLException;
	List<BlogPostDto> searchTitle();
	List<BlogPostDto> searchHash(String keyword) throws SQLException;
}
