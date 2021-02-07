package com.spring.web.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.SearchPostDao;
import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.SearchParam;

@Service
@Transactional
public class SearchPostServiceImpl implements SearchPostService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BlogPostDto> searchPost(SearchParam param) throws Exception {
		return sqlSession.getMapper(SearchPostDao.class).searchPost(param);
	}

}
