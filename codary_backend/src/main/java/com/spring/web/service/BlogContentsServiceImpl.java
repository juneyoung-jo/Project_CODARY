package com.spring.web.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
