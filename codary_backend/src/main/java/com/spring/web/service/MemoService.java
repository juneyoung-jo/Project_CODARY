package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.MemoContentsDto;

public interface MemoService {
	MemoContentsDto getMemo(int memoNum) throws Exception;
	int writeMemo(MemoContentsDto memo);
	List<MemoContentsDto> listMemo(String memoId) throws Exception;
	int modifyMemo(MemoContentsDto memo);
	int deleteMemo(int memoNum);
}
