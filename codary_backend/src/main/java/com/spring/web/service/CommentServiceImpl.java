package com.spring.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.CommentDao;
import com.spring.web.dto.CommentDto;
import com.spring.web.dto.CommentToLikeDto;
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
		map.put("blogContentsId", blogContentsId);
		return sqlSession.getMapper(CommentDao.class).listComment(map);
	}

	@Override
	public UserInfoDto getUserInfo(UserInfoDto info) throws Exception {
		// TODO Auto-generated method stub
		if (info == null)
			return null;
		return sqlSession.getMapper(CommentDao.class).getUserInfo(info);
	}

	@Override
	public void writeComment(CommentDto comment) throws Exception {
		if (comment == null)
			return;
		sqlSession.getMapper(CommentDao.class).writeComment(comment);

	}

	@Override
	public void deleteComment(int commentNum) throws Exception {
		sqlSession.getMapper(CommentDao.class).deleteComment(commentNum);

	}

	@Override
	public void modifyComment(CommentDto comment) throws Exception {
		if (comment == null)
			return;
		sqlSession.getMapper(CommentDao.class).modifyComment(comment);
	}

	@Override
	public boolean getCommentLike(CommentToLikeDto ctl) throws Exception {
		CommentToLikeDto ctlDto = sqlSession.getMapper(CommentDao.class).getCommentLike(ctl);
		if (ctlDto == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void commentLike(CommentToLikeDto ctl) throws Exception {
		sqlSession.getMapper(CommentDao.class).upComment(ctl);
		sqlSession.getMapper(CommentDao.class).commentLike(ctl);
	}

	@Override
	@Transactional
	public void commentLikeCancle(CommentToLikeDto ctl) throws Exception {
		int count = sqlSession.getMapper(CommentDao.class).commentLikeCancle(ctl);
		if(count != 0) {
			sqlSession.getMapper(CommentDao.class).downComment(ctl);
		}
	}

}
