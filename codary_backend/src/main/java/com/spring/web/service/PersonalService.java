package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.BlogContentsDto;

public interface PersonalService {
	public List<BlogContentsDto> personalContents(String blogid);
}
