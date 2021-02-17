package com.spring.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.SearchPostDao;
import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.HashtagDto;
import com.spring.web.dto.TestDto;

import io.jsonwebtoken.lang.Collections;

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
	@Transactional
	public List<BlogPostDto> searchByHash(List<Integer> keywords) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("num", keywords.size());
		map.put("keywords", keywords);
		List<Integer> list = mapper.searchByHash(map); // 검색 결과에 적합한 블로그 글 아이디 리스트
//		logger.info("결과 크기: " + results.size());
//		for(Integer i : results) {
//			logger.info("결과: " + i);
//		}

		List<BlogPostDto> result = mapper.getPostInfo(list); // BlogContents정보
		Map<String, String> profile;
		for (BlogPostDto post : result) {
			post.setCommentCnt(mapper.getCommentInfo(post.getBlogContentsId()).size());
			profile = mapper.getUserProfile(post.getBlogId());
			post.setProfile(profile.get("profile"));
			post.setNickname(profile.get("nickname"));
			post.setHashtags(mapper.getHashtagOfPost(post.getBlogContentsId()));
		}

		return result;
	}

	@Override
	@Transactional
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

		List<BlogPostDto> list = searchAll();

		List<BlogPostDto> result = new LinkedList<>();
		for (BlogPostDto bpd : list) {
			if (bpd.getBlogContentsTitle().contains(keyword)) {
//				bpd.setHashtags(mapper.getHashtagOfPost(bpd.getBlogContentsId()));
				result.add(bpd);
			}
		}

		return result;
	}

	@Override
	@Transactional
	@Cacheable(value = "get_Contents")
	public List<BlogPostDto> searchAll() throws Exception {
		List<BlogPostDto> list = mapper.searchTitle();
		List<BlogPostDto> result = new LinkedList<>();


		List<TestDto> map = mapper.getHashtagOfPostNew(123);
		
		if(map==null) return list;
		if(map.size()==0) return list;

		Map<Integer, List<HashtagDto>> test = new HashMap<Integer, List<HashtagDto>>();
		StringTokenizer st = null;
		StringTokenizer stt = null;
		String hashtagContent;
		int hashtagId;
		for (TestDto td : map) {
			test.put(td.getBlogContentsId(),new ArrayList<HashtagDto>());
			st = new StringTokenizer(td.getHashtagContent(), ",");
			int cnt = st.countTokens();
			for (int i = 0; i < cnt; i++) {
				HashtagDto hdto = new HashtagDto();
				stt = new StringTokenizer(st.nextToken()," ");
				hashtagContent = stt.nextToken();
				hashtagId = Integer.parseInt(stt.nextToken());
				
				
				hdto.setHashtagId(hashtagId);
				hdto.setHashtagContent(hashtagContent);
				test.get(td.getBlogContentsId()).add(hdto);
			}
		}
		
//		for(Integer a : test.keySet()) {
//			System.out.println(a);
//			System.out.println(test.get(a));
//		}

		for (BlogPostDto bpd : list) {
			if(test.get(bpd.getBlogContentsId()) == null) {
				result.add(bpd);
				continue;
			}
			bpd.setHashtags(test.get(bpd.getBlogContentsId()));
//			bpd.setHashtags(mapper.getHashtagOfPost(bpd.getBlogContentsId()));
			result.add(bpd);
		}
		return result;
	}

}
