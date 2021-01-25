package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.dao.PersonalDao;
import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogerLikeDto;
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
	public MemoDto showMemo(String memoid) {
		return personalDao.showMemo(memoid);
	}

	@Override
	public List<BlogerLikeDto> showLikeBloger(String uid) {
		return personalDao.likeBloger(uid);
	}
	
}
