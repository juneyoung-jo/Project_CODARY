package com.spring.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.MemoContentsDto;

@Mapper
public interface MemoDao {

	MemoContentsDto getMemo(int memoNum) throws SQLException;
	void writeMemo(MemoContentsDto memo) throws SQLException;
	List<MemoContentsDto> listMemo(String memoId) throws SQLException;
	int modifyMemo(MemoContentsDto memo);
	int deleteMemo(int memoNum);
	
}
