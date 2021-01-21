package com.spring.web.dto;

public class MemoContentsDto {
	private int memoNum;
	private int memoId;
	private String memoContent;
	private String memoTime;

	public int getMemoNum() {
		return memoNum;
	}

	public void setMemoNum(int memoNum) {
		this.memoNum = memoNum;
	}

	public int getMemoId() {
		return memoId;
	}

	public void setMemoId(int memoId) {
		this.memoId = memoId;
	}

	public String getMemoContent() {
		return memoContent;
	}

	public void setMemoContent(String memoContent) {
		this.memoContent = memoContent;
	}

	public String getMemoTime() {
		return memoTime;
	}

	public void setMemoTime(String memoTime) {
		this.memoTime = memoTime;
	}

	@Override
	public String toString() {
		return "MemoContentsDto [memoNum=" + memoNum + ", memoId=" + memoId + ", memoContent=" + memoContent
				+ ", memoTime=" + memoTime + "]";
	}

}
