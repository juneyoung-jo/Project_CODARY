package com.spring.web.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.BlogContentsDao;
import com.spring.web.dao.CommentDao;
import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogHashtagDto;
import com.spring.web.dto.HashtagDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

@Service
public class BlogContentsServiceImpl implements BlogContentsService {

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
	public int writeBlogContent(BlogContentsDto blogContent) throws Exception {
		return mapper.writeBlogContent(blogContent);
	}
	
	@Override
	public void writeHash(HashtagDto hash) throws Exception{
		mapper.writeHash(hash);
	}
	
	@Override
	public void writeBlogHash(BlogHashtagDto blogHash) throws Exception{
		mapper.writeBlogHash(blogHash);
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
	public List<Map<String, Object>> recommendBlogContents() throws Exception{
		List<Map<String, Object>> recommendList = new LinkedList<>(); //추천 글 리스트
		
		List<BlogContentsDto> list = mapper.getAllContents();
		Collections.sort(list, new Comparator<BlogContentsDto>() {
			@Override
			public int compare(BlogContentsDto o1, BlogContentsDto o2) {
				return o2.getBlogContentsView() + o2.getBlogContentsLike() - o1.getBlogContentsView() - o1.getBlogContentsLike();
			}
		});
		final int size = 3; //추천 글 갯수
		list = list.subList(0, size);
		
		for(BlogContentsDto post : list) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("blogId", post.getBlogId());
			m.put("blogContentsId", post.getBlogContentsId());
			m.put("blogContentsTitle", post.getBlogContentsTitle());
			m.put("blogConetentsCover", post.getBlogContentsCover());
			m.put("hashtags", mapper.getHashtagOfPost(post.getBlogContentsId()));
			recommendList.add(m);
		}
		
		return recommendList;
	}

	@Override
	@Transactional
	public BlogContentsDto writeLog(String uid, String blogId, int blogContentsId) throws Exception {
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("uid", uid);
		log.put("blogContentsId", blogContentsId);

		mapper.writeLog(log);
		mapper.increaseContentsView(blogContentsId); // 조회수 증가

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
		mapper.increasePostLike(like.getBlogContentsId());
	}

	@Override
	public void contentUnlike(BlogContentsLikeDto like) throws Exception {
		mapper.contentUnlike(like);
		mapper.decreasePostLike(like.getBlogContentsId());
	}

	@Override
	public UserInfoDto userInfo(String blogId) throws Exception {
		UserDto user = mapper.getUser(blogId);
		if (user == null)
			return null;
		info = new UserInfoDto();
		info.setUid(user.getUid());
		return commentMapper.getUserInfo(info);
	}

	@Override
	public List<HashtagDto> selectHash(String keyword) throws Exception {
		return mapper.selectHash(keyword);
	}

}
