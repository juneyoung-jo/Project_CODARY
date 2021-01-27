package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.MemoContentsDto;

public interface MemoService {
	MemoContentsDto getMemo(int memoNum) throws Exception;
	void writeMemo(MemoContentsDto memo) throws Exception;
	List<MemoContentsDto> listMemo(String memoId) throws Exception;
	void modifyMemo(MemoContentsDto memo) throws Exception;
	void deleteMemo(int memoNum) throws Exception;
}
