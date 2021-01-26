package com.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.dao.PersonalDao;
import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.MemoDto;

@Service
public class PersonalServiceImpl implements PersonalService{

	@Autowired
	private PersonalDao personalDao;
	
	@Override
	public List<BlogContentsDto> personalContents(int blogid) {
		return personalDao.showBlogContents(blogid);
	}

	@Override
	public MemoDto showMemo(int memoid) {
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
	
}
