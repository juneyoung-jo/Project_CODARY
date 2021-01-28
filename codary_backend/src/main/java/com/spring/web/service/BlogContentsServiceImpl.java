package com.spring.web.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.BlogContentsDao;
import com.spring.web.dto.BlogContentsDto;

@Service
public class BlogContentsServiceImpl implements BlogContentsService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public BlogContentsDto getContent(int blogContentsId) throws Exception {
		return sqlSession.getMapper(BlogContentsDao.class).getContent(blogContentsId);
	}

	@Override
	public void writeBlogContent(BlogContentsDto blogContent) throws Exception {
		sqlSession.getMapper(BlogContentsDao.class).writeBlogContent(blogContent);
	}

	@Override
	public List<BlogContentsDto> listBlogContents(String blogId) throws Exception {
		return sqlSession.getMapper(BlogContentsDao.class).listBlogContents(blogId);
	}

	@Override
	public void modifyBlogContent(BlogContentsDto blogContent) throws Exception {
		sqlSession.getMapper(BlogContentsDao.class).modifyBlogContent(blogContent);
	}

	@Override
	public void deleteBlogContent(int blogContentsId) throws Exception {
		sqlSession.getMapper(BlogContentsDao.class).deleteBlogContent(blogContentsId);
	}
	
	@Override
	public List<BlogContentsDto> recommendBlogContents() throws Exception{
		List<BlogContentsDto> list = sqlSession.getMapper(BlogContentsDao.class).getAllContents();
		
		return list.subList(0, 20);
	}

	@Override
	@Transactional
	public BlogContentsDto writeLog(String uid, String blogId, int blogContentsId) throws Exception {
		Map<String,Object> log = new HashMap<String, Object>();
		log.put("uid", uid);
		log.put("blogContentsId", blogContentsId);
		sqlSession.getMapper(BlogContentsDao.class).writeLog(log);
				
		return sqlSession.getMapper(BlogContentsDao.class).getContent(blogContentsId);
	}

}
