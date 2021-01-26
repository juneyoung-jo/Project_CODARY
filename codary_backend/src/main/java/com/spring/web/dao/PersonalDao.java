package com.spring.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.MemoDto;

@Mapper
public interface PersonalDao {
	public List<BlogContentsDto> showBlogContents(int blogid);
	public List<MemoContentsDto> showMemo(int memoid);
	public List<BlogDto> likeBloger(String uid);
	public List<BlogContentsDto> likeBlogContents(String uid);
}
