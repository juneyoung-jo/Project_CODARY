package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.BlogPostDto;

public interface SearchPostService {
	public List<BlogPostDto> searchPost() throws Exception;
}
