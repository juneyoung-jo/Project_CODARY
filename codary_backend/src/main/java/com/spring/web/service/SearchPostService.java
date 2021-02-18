package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.BlogPostDto;

public interface SearchPostService {
//	List<BlogPostDto> searchPost(SearchParam param) throws Exception;
	List<BlogPostDto> searchByHash(List<Integer> keywords) throws Exception;
	List<BlogPostDto> searchByTitle(String keyword) throws Exception;
	List<BlogPostDto> searchAll() throws Exception;
}
