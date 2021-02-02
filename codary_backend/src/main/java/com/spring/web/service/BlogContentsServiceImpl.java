package com.spring.web.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.BlogContentsDao;
import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;

@Service
public class BlogContentsServiceImpl implements BlogContentsService{

	@Autowired
	private BlogContentsDao mapper;
	
	@Override
	@Transactional
	public BlogContentsDto getContent(int blogContentsId) throws Exception {
		mapper.usergraphViewCount(blogContentsId);
		return mapper.getContent(blogContentsId);
	}

	@Override
	public void writeBlogContent(BlogContentsDto blogContent) throws Exception {
		mapper.writeBlogContent(blogContent);
	}

	@Override
	public List<BlogContentsDto> listBlogContents(String blogId) throws Exception {
		return mapper.listBlogContents(blogId);
	}

	@Override
	public int modifyBlogContent(BlogContentsDto blogContent) {
		return mapper.modifyBlogContent(blogContent);
	}

	@Override
	public int deleteBlogContent(int blogContentsId) {
		return mapper.deleteBlogContent(blogContentsId);
	}
	
	@Override
	public List<BlogContentsDto> recommendBlogContents() throws Exception{
		List<BlogContentsDto> list = mapper.getAllContents();
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
		
		mapper.writeLog(log);
		mapper.increaseContentsView(blogContentsId); //조회수 증가
				
		return mapper.getContent(blogContentsId);
	}

	@Override
	@Transactional
	public void increaseContentsView(int blogContentsId) throws Exception {
		mapper.increaseContentsView(blogContentsId);
	}

	@Override
	public BlogContentsLikeDto readBlogContentsLike(BlogContentsLikeDto like) throws Exception {
		return mapper.readContentLike(like);
	}

	@Override
	public void contentLike(BlogContentsLikeDto like) throws Exception {
		mapper.contentLike(like);
	}

	@Override
	public void contentUnlike(BlogContentsLikeDto like) throws Exception {
		mapper.contentUnlike(like);
	}
	
}
