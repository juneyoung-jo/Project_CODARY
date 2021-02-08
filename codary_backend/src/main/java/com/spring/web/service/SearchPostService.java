package com.spring.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.SearchParam;

public interface SearchPostService {
	List<BlogPostDto> searchPost(SearchParam param) throws Exception;
	List<BlogPostDto> orderByDate(String keyword) throws Exception;
	List<BlogPostDto> orderByDate() throws Exception;
	List<BlogPostDto> orderByView(String keyword) throws Exception;
	List<BlogPostDto> orderByView() throws Exception;
	List<BlogPostDto> orderByLike(String keyword) throws Exception;
	List<BlogPostDto> orderByLike() throws Exception;
}
