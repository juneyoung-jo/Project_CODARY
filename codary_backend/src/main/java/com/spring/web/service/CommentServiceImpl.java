package com.spring.web.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.dao.CommentDao;
import com.spring.web.dto.CommentDto;
import com.spring.web.dto.CommentToLikeDto;
import com.spring.web.dto.LogDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<CommentDto> listComment(String blogId, int blogContentsId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
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
		if (count != 0) {
			sqlSession.getMapper(CommentDao.class).downComment(ctl);
		}
	}

	@Override
	public List<CommentDto> commentCheck(UserDto user) throws Exception {
		// 1. uid를 가지고 log 테이블에서 blog_content_id에 접속한 시간 셀렉
		List<LogDto> log = sqlSession.getMapper(CommentDao.class).getLog(user);
		HashMap<Integer, String> mylogTime = new HashMap<Integer, String>();
		
		// 2. 1번의 결과물에서 각각의 bolg_content_id 마다 가장 늦은 datetime 저장
		for (LogDto lg : log) {
			mylogTime.put(lg.getBlogContentsId(), lg.getDatetime());
		}
//		for(Integer in : mylogTime.keySet() ) {
//			System.out.println(in + " : " + mylogTime.get(in));
//		}
//		LocalDateTime t1 = null;
//		LocalDateTime t2 = null;
//		for (LogDto lg : log) {
//			if (mylogTime.get(Integer.toString(lg.getBlogContentsId())) == null) {
//				mylogTime.put(Integer.toString(lg.getBlogContentsId()), lg.getDatetime());
//			} else {
//				System.out.println();
//				t1 = LocalDateTime.parse(mylogTime.get(Integer.toString(lg.getBlogContentsId())), 
//						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//				t2 = LocalDateTime.parse(lg.getDatetime(), 
//						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//				
//				
//			}
//		}


		// 3. blogId 가지고 comment 전부 다 셀렉
		List<CommentDto> 

		// 4. 2번과 3번의 date time을 비교하여 map 형식으로 던져줌
		return null;
	}

}
