package com.spring.web.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.dao.MemoDao;
import com.spring.web.dto.MemoContentsDto;

@Service
public class MemoServiceImpl implements MemoService{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemoContentsDto getMemo(int memoNum) throws Exception {
		return sqlSession.getMapper(MemoDao.class).getMemo(memoNum);
	}
	
	@Override
	public void writeMemo(MemoContentsDto memo) throws Exception {
		sqlSession.getMapper(MemoDao.class).writeMemo(memo);
	}

	@Override
	public List<MemoContentsDto> listMemo(String memoId) throws Exception {
		return sqlSession.getMapper(MemoDao.class).listMemo(memoId);
	}

	@Override
	public void modifyMemo(MemoContentsDto memo) throws Exception {
		sqlSession.getMapper(MemoDao.class).modifyMemo(memo);
	}

	@Override
	public void deleteMemo(int memoNum) throws Exception {
		sqlSession.getMapper(MemoDao.class).deleteMemo(memoNum);
	}

}
