package com.spring.web.dto;

public class MemoDto {

	private int memoId;

	public int getMemoId() {
		return memoId;
	}

	public void setMemoId(int memoId) {
		this.memoId = memoId;
	}

	@Override
	public String toString() {
		return "MemoDto [memoId=" + memoId + "]";
	}

}
