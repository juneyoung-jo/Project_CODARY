package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.MemoDto;

public interface PersonalService {
	public List<BlogContentsDto> personalContents(String blogid);
	public MemoDto showMemo(String memoid);
}
