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
	private MemoDao mapper;
	
	@Override
	public MemoContentsDto getMemo(int memoNum) throws Exception {
		return mapper.getMemo(memoNum);
	}
	
	@Override
	public int writeMemo(MemoContentsDto memo) {
		return mapper.writeMemo(memo);
	}

	@Override
	public List<MemoContentsDto> listMemo(String memoId) throws Exception {
		return mapper.listMemo(memoId);
	}

	@Override
	public int modifyMemo(MemoContentsDto memo) {
		return mapper.modifyMemo(memo);
	}

	@Override
	public int deleteMemo(int memoNum) {
		return mapper.deleteMemo(memoNum);
	}

}
