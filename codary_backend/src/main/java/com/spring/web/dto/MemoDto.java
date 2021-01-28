package com.spring.web.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "MemoDto : 메모 아이디",description = "유저 별 가지고 있는 메모 아이디")
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
