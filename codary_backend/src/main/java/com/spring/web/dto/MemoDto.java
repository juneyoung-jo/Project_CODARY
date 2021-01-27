package com.spring.web.dto;

public class MemoDto {

	private String memoId;
	
	public MemoDto() {
	}

	public MemoDto(String memoId) {
		this.memoId = memoId;
	}

	public String getMemoId() {
		return memoId;
	}

	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}

	@Override
	public String toString() {
		return "MemoDto [memoId=" + memoId + "]";
	}



}
