package com.spring.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public BlogContentsDto getContent(int blogId, int blogContentsId) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		map.put("blogId", blogId);
		map.put("blogContentsId", blogContentsId);
		return sqlSession.getMapper(BlogContentsDao.class).getContent(map);
	}

	@Override
	public void writeBlogContent(BlogContentsDto blogContent) throws Exception {
		sqlSession.getMapper(BlogContentsDao.class).writeBlogContent(blogContent);
	}

	@Override
	public List<BlogContentsDto> listBlogContents(int blogId) throws Exception {
		return sqlSession.getMapper(BlogContentsDao.class).listBlogContents(blogId);
	}

	@Override
	public void modifyBlogContent(BlogContentsDto blogContent) throws Exception {
		sqlSession.getMapper(BlogContentsDao.class).modifyBlogContent(blogContent);
	}

	@Override
	public void deleteBlogContent(int blogId, int blogContentsId) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		map.put("blogId", blogId);
		map.put("blogContentsId", blogContentsId);
		sqlSession.getMapper(BlogContentsDao.class).deleteBlogContent(map);
	}

}
