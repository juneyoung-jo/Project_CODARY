package com.spring.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
		List<BlogContentsDto> recommendList; //추천 글 리스트
		
		final int size = 3; //추천 글 갯수
		Set<BlogContentsDto> set = new HashSet<>();
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Iterator<BlogContentsDto> iter = list.iterator();
		
		long cur = System.currentTimeMillis()/(24*60*60*1000);
		while(iter.hasNext()) {
			BlogContentsDto blog = iter.next();
			//날짜 차이 구하기
			long date = transFormat.parse(blog.getBlogDatetime().substring(0, 10)).getTime()/(24*60*60*1000);
			long sub = cur - date;
			if(sub < 90)
				set.add(blog);
		}
		recommendList = new ArrayList<BlogContentsDto>(set);
		Collections.sort(recommendList, new Comparator<BlogContentsDto>() {
			@Override
			public int compare(BlogContentsDto o1, BlogContentsDto o2) {
				return o2.getBlogContentsView() + o2.getBlogContentsLike()*3 - o1.getBlogContentsView() - o1.getBlogContentsLike()*3;
			}
		});
		
		return recommendList.subList(0, size);
	}

	@Override
	@Transactional
	public BlogContentsDto writeLog(String uid, String blogId, int blogContentsId) throws Exception {
		Map<String,Object> log = new HashMap<String, Object>();
		log.put("uid", uid);
		log.put("blogContentsId", blogContentsId);
		sqlSession.getMapper(BlogContentsDao.class).writeLog(log);
		sqlSession.getMapper(BlogContentsDao.class).increaseContentsView(blogContentsId); //조회수 증가
				
		return sqlSession.getMapper(BlogContentsDao.class).getContent(blogContentsId);
	}

	@Override
	@Transactional
	public void increaseContentsView(int blogContentsId) throws Exception {
		sqlSession.getMapper(BlogContentsDao.class).increaseContentsView(blogContentsId);
	}
	
}
