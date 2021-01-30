package com.spring.web.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
		List<BlogContentsDto> recommendList = new LinkedList<>(); //추천 글 리스트
		
		final int size = 20; //추천 글 갯수
		Map<Integer, BlogContentsDto> map = new TreeMap<>();
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Iterator<BlogContentsDto> iter = list.iterator();
		
		long cur = System.currentTimeMillis()/(1000*60*60);
		while(iter.hasNext()) {
			BlogContentsDto blog = iter.next();
			//날짜 차이 구하기
			long date = transFormat.parse(blog.getBlogDatetime().substring(0, 10)).getTime()/(1000*60*60);
			long sub = cur - date;
			if(sub < 90*60)
				map.put((int) (blog.getBlogContentsView() + blog.getBlogContentsLike()*3 - (int)sub/1000), blog);
		}
		
		int cnt = 0;
		for(int key : map.keySet()) {
			recommendList.add(map.get(key));
			System.out.println(map.get(key).toString());
			if(++cnt == size) break;
		}
		
		return recommendList;
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
