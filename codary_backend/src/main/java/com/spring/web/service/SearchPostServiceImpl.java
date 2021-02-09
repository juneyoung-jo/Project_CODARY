package com.spring.web.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
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

	@Override
	public List<BlogPostDto> orderByDate(String keyword) throws Exception {
		List<BlogPostDto> list;
		if(keyword.charAt(0) == '#')
			list = sqlSession.getMapper(SearchPostDao.class).searchHash(keyword);
		else {
			list = new LinkedList<>();
			List<BlogPostDto> result = sqlSession.getMapper(SearchPostDao.class).searchTitle();
			for(BlogPostDto bpd : result) {
				if(bpd.getBlogContentsTitle().contains(keyword))
					list.add(bpd);
			}
		}
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogDatetime().compareTo(o1.getBlogDatetime());
			}
		});
		return list;
	}

	@Override
	public List<BlogPostDto> orderByDate() throws Exception {
		List<BlogPostDto> list = sqlSession.getMapper(SearchPostDao.class).searchTitle();
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogDatetime().compareTo(o1.getBlogDatetime());
			}
		});
		return list;
	}

	@Override
	public List<BlogPostDto> orderByView(String keyword) throws Exception {
		List<BlogPostDto> list;
		if(keyword.charAt(0) == '#')
			list = sqlSession.getMapper(SearchPostDao.class).searchHash(keyword);
		else {
			list = new LinkedList<>();
			List<BlogPostDto> result = sqlSession.getMapper(SearchPostDao.class).searchTitle();
			for(BlogPostDto bpd : result) {
				if(bpd.getBlogContentsTitle().contains(keyword))
					list.add(bpd);
			}
		}
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogContentsView() - o1.getBlogContentsView();
			}
		});
		return list;
	}

	@Override
	public List<BlogPostDto> orderByView() throws Exception {
		List<BlogPostDto> list = sqlSession.getMapper(SearchPostDao.class).searchTitle();
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogContentsView() - o1.getBlogContentsView();
			}
		});
		return list;
	}

	@Override
	public List<BlogPostDto> orderByLike(String keyword) throws Exception {
		List<BlogPostDto> list;
		if(keyword.charAt(0) == '#')
			list = sqlSession.getMapper(SearchPostDao.class).searchHash(keyword);
		else {
			list = new LinkedList<>();
			List<BlogPostDto> result = sqlSession.getMapper(SearchPostDao.class).searchTitle();
			for(BlogPostDto bpd : result) {
				if(bpd.getBlogContentsTitle().contains(keyword))
					list.add(bpd);
			}
		}
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogContentsLike() - o1.getBlogContentsLike();
			}
		});
		return list;
	}

	@Override
	public List<BlogPostDto> orderByLike() throws Exception {
		List<BlogPostDto> list = sqlSession.getMapper(SearchPostDao.class).searchTitle();
		Collections.sort(list, new Comparator<BlogPostDto>() {
			@Override
			public int compare(BlogPostDto o1, BlogPostDto o2) {
				return o2.getBlogContentsLike() - o1.getBlogContentsLike();
			}
		});
		return list;
	}

}
