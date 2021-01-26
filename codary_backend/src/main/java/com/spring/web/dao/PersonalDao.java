package com.spring.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.MemoDto;

@Mapper
public interface PersonalDao {
	public List<BlogContentsDto> showBlogContents(int blogid);
	public MemoDto showMemo(int memoid);
	public List<BlogerLikeDto> likeBloger(String uid);
	public List<BlogContentsDto> likeBlogContents(String uid);
}
