package com.spring.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.web.dao.MemoDao;
import com.spring.web.dto.MemoContentsDto;

@SpringBootTest
public class memoTest {
	@Autowired
	MemoDao dao;
	
	@Test
	public void selectTest() {
		List<MemoContentsDto> memos;
		try {
			memos = dao.listMemo("12");
			assertEquals(memos.size(), 2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertTest() {
		MemoContentsDto memo = new MemoContentsDto();
		memo.setMemoId("12");
		memo.setMemoContent("insert Test");
		memo.setMemoTime("");
		
		try {
			dao.writeMemo(memo);
			List<MemoContentsDto> memos;
			memos = dao.listMemo("12");
			assertEquals(memos.size(), 3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void modifyTest() {
		MemoContentsDto memo = new MemoContentsDto();
		memo.setMemoId("12");
		memo.setMemoNum(4);
		memo.setMemoContent("update Test");
		memo.setMemoTime("");
		try {
			dao.modifyMemo(memo);
			assertEquals("update Test", dao.getMemo(4).getMemoContent());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest() {
		try {
			dao.deleteMemo(5);
			List<MemoContentsDto> memos;
			memos = dao.listMemo("12");
			assertEquals(memos.size(), 2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
