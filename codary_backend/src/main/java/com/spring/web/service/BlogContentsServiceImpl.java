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
import com.spring.web.dao.CommentDao;
import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

@Service
public class BlogContentsServiceImpl implements BlogContentsService{

	@Autowired
	private BlogContentsDao mapper;
	
	@Autowired
	private CommentDao commentMapper;
	
	private UserInfoDto info = null;
	
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
	public List<BlogPostDto> recommendByDate() throws Exception{
		List<BlogPostDto> list = mapper.getAllContents();
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogDatetime().compareTo(o1.getBlogDatetime());
			}
		});
		return list;
	}
	
	@Override
	public List<BlogPostDto> recommendByLike() throws Exception{
		List<BlogPostDto> list = mapper.getAllContents();
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogContentsLike() - o1.getBlogContentsLike();
			}
		});
		return list;
	}
	
	@Override
	public List<BlogPostDto> recommendByView() throws Exception{
		List<BlogPostDto> list = mapper.getAllContents();
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogContentsView() - o1.getBlogContentsView();
			}
		});
		return list;
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

	@Override
	public UserInfoDto userInfo(String blogId) throws Exception {
		UserDto user = mapper.getUser(blogId);
		if(user == null) return null;
		info = new UserInfoDto();
		info.setUid(user.getUid());
		return commentMapper.getUserInfo(info);
	}
	
}
