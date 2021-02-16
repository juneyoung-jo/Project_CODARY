package com.spring.web.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.UserInfoDto;

public interface PersonalService {
	public List<BlogPostDto> personalContents(String blogid) throws Exception;
	public List<MemoContentsDto> showMemo(String memoid);
	public List<Map<String, Object>> showLikeBloger(String uid);
	public List<BlogPostDto> showLikeBlogContents(String uid) throws Exception;
	public List<Map<String, Integer>> jandi(String blogid) throws ParseException;
	public List<Map<String, Integer>> usergraph(String blogid) throws ParseException;
	public void blogerLike(BlogerLikeDto bld);
	public void blogerUnlike(BlogerLikeDto bld);
	public boolean readBlogerLike(BlogerLikeDto bld);
	public UserInfoDto findUser(String blogid);
	public BlogDto findBlog(String blogid);
	public void modifyBlogCover(Map<String, Object> cover);
}
