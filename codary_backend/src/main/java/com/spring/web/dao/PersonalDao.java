package com.spring.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.JandiDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.MemoDto;
import com.spring.web.dto.UserInfoDto;
import com.spring.web.dto.UsergraphDto;

@Mapper
public interface PersonalDao {
	public List<BlogContentsDto> showBlogContents(String blogid);
	public List<MemoContentsDto> showMemo(String memoid);
	public List<Map<String, Object>> likeBloger(String uid);
	public List<BlogContentsDto> likeBlogContents(String uid);
	public List<JandiDto> jandi(String blogid);
	public List<UsergraphDto> usergraph(String blogid); 
	//public void usergraphViewCount(String blogid);
	public void blogerLike(BlogerLikeDto bld);
	public void blogerUnlike(BlogerLikeDto bld);
	public BlogerLikeDto readBlogerLike(BlogerLikeDto bld);
	public UserInfoDto findUser(String blogid);
}
