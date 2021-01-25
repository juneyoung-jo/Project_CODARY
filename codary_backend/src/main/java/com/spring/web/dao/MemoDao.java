package com.spring.web.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.MemoContentsDto;

@Mapper
public interface MemoDao {

	void writeMemo(MemoContentsDto memo) throws SQLException;
	List<MemoContentsDto> listMemo(int memoId) throws SQLException;
	void modifyMemo(MemoContentsDto memo) throws SQLException;
	void deleteMemo(int memoNum) throws SQLException;
	
}
