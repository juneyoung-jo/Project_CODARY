package com.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.SearchParam;
import com.spring.web.service.SearchPostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchPostService searchPostService;

	public static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	
	/**
	 * 블로그 게시글 목록 호출
	 * 
	 * @param -
	 * @return List<BlogPostDto>
	 */
	@ApiOperation(value = "게시글 목록 호출", notes = "@param :  </br> @return List<BlogPostDto>")
	@GetMapping("/post")
	public ResponseEntity<Map<String, Object>> getPost(
			@RequestParam Map<String, String> param) {
		
		String currentPage_ = param.get("currentPage");
		String spp_ = param.get("spp");
		String keyword = param.get("keyword") != null? param.get("keyword") : "";
		int spp = spp_ !=null? Integer.parseInt(spp_) : 30;
		int currentPage = currentPage_ !=null? Integer.parseInt(currentPage_) : 1;
		int start = (currentPage-1)*spp;

		// 검색을 위한 파라미터 객체 생성
		SearchParam searchParam = new SearchParam();
		searchParam.setKeyword(keyword != null? keyword : "");
		searchParam.setStart(start);
		searchParam.setSpp(spp);
		
		System.out.println("#검색 파라미터: " + searchParam.toString());
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (keyword != null && keyword != "") {
			System.out.println("#keyword ::" + keyword);
			keyword = keyword.trim();
			
			// 1. 제목, 내용 검색
			if (keyword.charAt(0) != '#') {
				logger.info("#제목, 내용 검색");
				try {
					resultMap.put("list", searchPostService.searchPost(searchParam));
				} catch (Exception e) {
					e.printStackTrace();
					resultMap.put("message", FAIL);
				}

			} else {
				// 2. 태그 검색
				logger.info("#해쉬 태그 검색");
				keyword = keyword.replace(" ", "");
				try {
					resultMap.put("list", searchPostService.searchPostByTag(searchParam));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}else {
			System.out.println("#keyword 생략!!!");
			try {
				resultMap.put("list", searchPostService.searchPost(searchParam));
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("message", FAIL);
			}
		}

		resultMap.put("start", 0);
		resultMap.put("message", SUCCESS);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

}
