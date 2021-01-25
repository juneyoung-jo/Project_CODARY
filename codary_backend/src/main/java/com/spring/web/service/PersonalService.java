package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.MemoDto;

public interface PersonalService {
	public List<BlogContentsDto> personalContents(int blogid);
	public MemoDto showMemo(int memoid);
	public List<BlogerLikeDto> showLikeBloger(String uid);
	public List<BlogContentsLikeDto> showLikeBlogContents(String uid);
}
