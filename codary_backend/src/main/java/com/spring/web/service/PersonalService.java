package com.spring.web.service;

import java.util.HashMap;
import java.util.List;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.JandiDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.MemoDto;

public interface PersonalService {
	public List<BlogContentsDto> personalContents(String blogid);
	public List<MemoContentsDto> showMemo(String memoid);
	public List<BlogDto> showLikeBloger(String uid);
	public List<BlogContentsDto> showLikeBlogContents(String uid);
	public List<JandiDto> jandi(String blogid);
}
