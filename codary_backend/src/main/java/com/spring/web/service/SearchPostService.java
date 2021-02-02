package com.spring.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.spring.web.dto.BlogPostDto;

public interface SearchPostService {
	public List<BlogPostDto> searchPost(String keyword) throws Exception;
}
