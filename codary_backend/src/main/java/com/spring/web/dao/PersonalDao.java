package com.spring.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.MemoDto;

@Mapper
public interface PersonalDao {
	public List<BlogContentsDto> showBlogContents(String blogid);
	public MemoDto showMemo(String memoid);
	public List<BlogerLikeDto> likeBloger(String uid);
}
