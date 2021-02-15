package com.spring.web.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "MemoContentstDto : 메모 내용",description = "메모 내용")
public class MemoContentsDto {
	private int memoNum;
	private String memoId;
	private String memoContent;
	private String memoLink;
	private String memoTime;
	private byte isdeleted;

	public int getMemoNum() {
		return memoNum;
	}

	public void setMemoNum(int memoNum) {
		this.memoNum = memoNum;
	}

	public String getMemoId() {
		return memoId;
	}

	public void setMemoId(String memoId) {
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

	public byte getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(byte isdeleted) {
		this.isdeleted = isdeleted;
	}
	
	public String getMemoLink() {
		return memoLink;
	}

	public void setMemoLink(String memoLink) {
		this.memoLink = memoLink;
	}

	@Override
	public String toString() {
		return "MemoContentsDto [memoNum=" + memoNum + ", memoId=" + memoId + ", memoContent=" + memoContent
				+ ", memoTime=" + memoTime + ", isdeleted=" + isdeleted + ", memoLink=" + memoLink + "]";
	}

}
