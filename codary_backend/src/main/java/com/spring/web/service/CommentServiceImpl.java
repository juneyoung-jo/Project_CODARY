package com.spring.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.dao.CommentDao;
import com.spring.web.dto.CommentDto;
import com.spring.web.dto.UserInfoDto;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<CommentDto> listComment(int blogId, int blogContentsId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<>();
		map.put("blogId", blogId);
		map.put("blogContentsId",blogContentsId);
		return sqlSession.getMapper(CommentDao.class).listComment(map);
	}

	@Override
	public UserInfoDto getUserInfo(UserInfoDto info) throws Exception {
		// TODO Auto-generated method stub
		if(info == null) return null;
		return sqlSession.getMapper(CommentDao.class).getUserInfo(info);
	}

	@Override
	public void writeComment(CommentDto comment) throws Exception {
		if(comment == null ) return;
		sqlSession.getMapper(CommentDao.class).writeComment(comment);
		
	}

	@Override
	public void deleteComment(int commentNum) throws Exception {
		sqlSession.getMapper(CommentDao.class).deleteComment(commentNum);
		
	}

}
