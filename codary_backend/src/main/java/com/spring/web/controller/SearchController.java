package com.spring.web.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.BlogPostDto;
import com.spring.web.service.SearchPostService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchPostService searchPostService;

	public static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	/**
	 * 제목 검색 - 검색어 없 - 최신순
	 * 
	 * @param 
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "제목 검색 - 검색어 없 - 최신순", notes = "@param </br> @return BlogContentsDto")
	@GetMapping("date")
	public ResponseEntity<List<BlogPostDto>> orderByDate() throws Exception{
		try {
			List<BlogPostDto> list = searchPostService.searchAll();
			Collections.sort(list, new Comparator<BlogPostDto>() {
				@Override
				public int compare(BlogPostDto o1, BlogPostDto o2) {
					return o2.getBlogDatetime().compareTo(o1.getBlogDatetime());
				}
			});
			return new ResponseEntity<List<BlogPostDto>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * 제목 검색 - 검색어 있 - 최신순
	 * 
	 * @param 
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "제목 검색 - 검색어 있 - 최신순", notes = "@param </br> @return BlogContentsDto")
	@GetMapping("date/{keyword}")
	public ResponseEntity<List<BlogPostDto>> orderByDate(@PathVariable String keyword) throws Exception{
		try {
			List<BlogPostDto> list = searchPostService.searchByTitle(keyword);
			Collections.sort(list, new Comparator<BlogPostDto>() {
				@Override
				public int compare(BlogPostDto o1, BlogPostDto o2) {
					return o2.getBlogDatetime().compareTo(o1.getBlogDatetime());
				}
			});
			return new ResponseEntity<List<BlogPostDto>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * 제목 검색 - 검색어 없 - 좋아요순
	 * 
	 * @param 
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "제목 검색 - 검색어 없 - 좋아요순", notes = "@param </br> @return BlogContentsDto")
	@GetMapping("like")
	public ResponseEntity<List<BlogPostDto>> orderByLike() throws Exception{
		try {
			List<BlogPostDto> list = searchPostService.searchAll();
			Collections.sort(list, new Comparator<BlogPostDto>() {
				@Override
				public int compare(BlogPostDto o1, BlogPostDto o2) {
					return o2.getBlogContentsLike() - o1.getBlogContentsLike();
				}
			});
			return new ResponseEntity<List<BlogPostDto>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * 제목 검색 - 검색어 있 - 좋아요순
	 * 
	 * @param String
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "제목 검색 - 검색어 있 - 좋아요순", notes = "@param String </br> @return BlogContentsDto")
	@GetMapping("like/{keyword}")
	public ResponseEntity<List<BlogPostDto>> orderByLike(@PathVariable String keyword) throws Exception{
		try {
			List<BlogPostDto> list = searchPostService.searchByTitle(keyword);
			Collections.sort(list, new Comparator<BlogPostDto>() {
				@Override
				public int compare(BlogPostDto o1, BlogPostDto o2) {
					return o2.getBlogContentsLike() - o1.getBlogContentsLike();
				}
			});
			return new ResponseEntity<List<BlogPostDto>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * 제목 검색 - 검색어 없 - 조회순
	 * 
	 * @param 
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "제목 검색 - 검색어 없 - 조회순", notes = "@param </br> @return BlogContentsDto")
	@GetMapping("view")
	public ResponseEntity<List<BlogPostDto>> orderByView() throws Exception{
		try {
			List<BlogPostDto> list = searchPostService.searchAll();
			Collections.sort(list, new Comparator<BlogPostDto>() {
				@Override
				public int compare(BlogPostDto o1, BlogPostDto o2) {
					return o2.getBlogContentsView() - o1.getBlogContentsView();
				}
			});
			return new ResponseEntity<List<BlogPostDto>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * 제목 검색 - 검색어 있 - 조회순
	 * 
	 * @param String
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "제목 검색 - 검색어 있 - 조회순", notes = "@param String </br> @return BlogContentsDto")
	@GetMapping("view/{keyword}")
	public ResponseEntity<List<BlogPostDto>> orderByView(@PathVariable String keyword) throws Exception{
		try {
			List<BlogPostDto> list = searchPostService.searchByTitle(keyword);
			Collections.sort(list, new Comparator<BlogPostDto>() {
				@Override
				public int compare(BlogPostDto o1, BlogPostDto o2) {
					return o2.getBlogContentsView() - o1.getBlogContentsView();
				}
			});
			return new ResponseEntity<List<BlogPostDto>>(list, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * 해쉬태크  검색 - 검색어 있
	 * 
	 * @param String
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "해쉬태크  검색 - 검색어 있", notes = "@param String </br> @return BlogContentsDto")
	@PostMapping("date")
	public ResponseEntity<List<BlogPostDto>> searchByHash(@RequestBody List<Integer> keywords) throws Exception{
		try {
			//임시데이터
			keywords = new LinkedList<>();
			keywords.add(3);
			keywords.add(1);
			return new ResponseEntity<List<BlogPostDto>>(searchPostService.searchByHash(keywords), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
//	/**
//	 * 블로그 게시글 목록 호출
//	 * 
//	 * @param -
//	 * @return List<BlogPostDto>
//	 */
//	@ApiOperation(value = "게시글 목록 호출", notes = "@param :  </br> @return List<BlogPostDto>")
//	@GetMapping("/post")
//	public ResponseEntity<Map<String, Object>> getPost(
//			@RequestParam Map<String, String> param) {
//		
//		String currentPage_ = param.get("currentPage");
//		String spp_ = param.get("spp");
//		String keyword = param.get("keyword") != null? param.get("keyword") : "";
//		int spp = spp_ !=null? Integer.parseInt(spp_) : 30;
//		int currentPage = currentPage_ !=null? Integer.parseInt(currentPage_) : 1;
//		int start = (currentPage-1)*spp;
//
//		// 검색을 위한 파라미터 객체 생성
//		SearchParam searchParam = new SearchParam();
//		searchParam.setKeyword(keyword != null? keyword : "");
//		searchParam.setStart(start);
//		searchParam.setSpp(spp);
//		
//		System.out.println("#검색 파라미터: " + searchParam.toString());
//		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		if (keyword != null && keyword != "") {
//			System.out.println("#keyword ::" + keyword);
//			keyword = keyword.trim();
//			
//			// 1. 제목, 내용 검색
//			if (keyword.charAt(0) != '#') {
//				logger.info("#제목, 내용 검색");
//				try {
//					resultMap.put("list", searchPostService.searchPost(searchParam));
//				} catch (Exception e) {
//					e.printStackTrace();
//					resultMap.put("message", FAIL);
//				}
//
//			} else {
//				// 2. 태그 검색
//				logger.info("#해쉬 태그 검색");
//				keyword = keyword.replace(" ", "");
//				
//
//			}
//		}else {
//			System.out.println("#keyword 생략!!!");
//			try {
//				resultMap.put("list", searchPostService.searchPost(searchParam));
//			} catch (Exception e) {
//				e.printStackTrace();
//				resultMap.put("message", FAIL);
//			}
//		}
//
//		resultMap.put("start", 0);
//		resultMap.put("message", SUCCESS);
//		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
//	}

}
