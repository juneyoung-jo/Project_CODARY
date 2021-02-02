package com.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.service.SearchPostService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchPostService searchPostService;

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
			@RequestParam Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String keyword = "";
		if (param.get("keyword") != null && param.get("keyword") != "") {
			System.out.println("#keyword ::" + (String) param.get("keyword"));
			keyword = (String) param.get("keyword");
			keyword = keyword.replace(" ", "");
			// 1. 제목, 내용 검색
			if (keyword.charAt(0) != '#') {
				try {
					resultMap.put("list", searchPostService.searchPost(keyword));
				} catch (Exception e) {
					e.printStackTrace();
					resultMap.put("message", FAIL);
				}

			} else {
				// 2. 태그 검색

			}
		}else {
			System.out.println("keyword 안넘어옴!!!");
			try {
				resultMap.put("list", searchPostService.searchPost(keyword));
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
