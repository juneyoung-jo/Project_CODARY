package com.spring.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.web.dto.BlogPostDto;

@Repository
public interface SearchPostDao {

	public List<BlogPostDto> searchPost(String keyword) throws SQLException;
}
