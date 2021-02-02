package com.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.BlogPostDto;
import com.spring.web.service.SearchPostService;


@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchPostService searchPostService; 
	
	@GetMapping("/post")
	public List<BlogPostDto> getPost(){
		try {
			return searchPostService.searchPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<BlogPostDto> result = new ArrayList<BlogPostDto>();
		return result;
	}
	
	
}
