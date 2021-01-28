package com.spring.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.dao.PersonalDao;
import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.JandiDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.MemoDto;

@Service
public class PersonalServiceImpl implements PersonalService{

	@Autowired
	private PersonalDao personalDao;
	
	@Override
	public List<BlogContentsDto> personalContents(String blogid) {
		return personalDao.showBlogContents(blogid);
	}

	@Override
	public List<MemoContentsDto> showMemo(String memoid) {
		return personalDao.showMemo(memoid);
	}

	@Override
	public List<BlogDto> showLikeBloger(String uid) {
		return personalDao.likeBloger(uid);
	}

	@Override
	public List<BlogContentsDto> showLikeBlogContents(String uid) {
		return personalDao.likeBlogContents(uid);
	}

	@Override
	public List<JandiDto> jandi(String blogid) {
		return personalDao.jandi(blogid);
	}
	
}
