package com.spring.web.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.SearchPostDao;
import com.spring.web.dto.BlogPostDto;

@Service
@Transactional
public class SearchPostServiceImpl implements SearchPostService {

	@Autowired
	private SearchPostDao mapper;

	public static final Logger logger = LoggerFactory.getLogger(SearchPostServiceImpl.class);

//	@Override
//	public List<BlogPostDto> searchPost(SearchParam param) throws Exception {
//		return sqlSession.getMapper(SearchPostDao.class).searchPost(param);
//	}

	@Override
	public List<BlogPostDto> searchByHash(List<Integer> keywords) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("num", keywords.size());
		map.put("keywords", keywords);
		List<Integer> list = mapper.searchByHash(map); // 검색 결과에 적합한 블로그 글 아이디 리스트
//		logger.info("결과 크기: " + results.size());
//		for(Integer i : results) {
//			logger.info("결과: " + i);
//		}

		List<BlogPostDto> result = mapper.getPostInfo(list); //BlogContents정보
		Map<String, String> profile;
		for(BlogPostDto post: result) {
			post.setCommentCnt(mapper.getCommentInfo(post.getBlogContentsId()).size());
			profile = mapper.getUserProfile(post.getBlogId());
			post.setProfile(profile.get("profile"));
			post.setNickname(profile.get("nickname"));
		}

		return result;
	}

	@Override
	public List<BlogPostDto> searchByTitle(String keyword) throws Exception {
		// List<Map<String, Object>> list = mapper.searchByTitle();
		// List<Integer> results = new LinkedList<>();
		// for(Map<String, Object> map : list) {
		// if(map.get("blog_contents_title").toString().contains(keyword))
		// results.add((Integer) map.get("blog_contents_id"));
		// }
		//
		// for(Map<String, Object> map : list) {
		// if(map.get("blog_contents_title").toString().contains(keyword))
		// results.add((Integer) map.get("blog_contents_id"));
		// }
		//
		//
		//
		// return null;
		
		List<BlogPostDto> list = mapper.searchTitle();
		
		List<BlogPostDto> result = new LinkedList<>();
		for(BlogPostDto bpd : list) {
			if(bpd.getBlogContentsTitle().contains(keyword))
				result.add(bpd);
		}
		
		return result;
	}

	@Override
	@Cacheable(value = "get_Contents")
	public List<BlogPostDto> searchAll() throws Exception {
		return mapper.searchTitle();
	}

}
