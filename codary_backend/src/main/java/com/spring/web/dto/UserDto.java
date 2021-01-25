package com.spring.web.dto;

public class UserDto {

	private String uid;
	private int blogId;
	private int memoId;
	private String aulKey;
	private String aulId;
	private String aulDatetime;

	public UserDto() {
	}
	public UserDto(String uid, int blogId, int memoId, String aulKey, String aulId, String aulDatetime) {
		super();
		this.uid = uid;
		this.blogId = blogId;
		this.memoId = memoId;
		this.aulKey = aulKey;
		this.aulId = aulId;
		this.aulDatetime = aulDatetime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public int getMemoId() {
		return memoId;
	}

	public void setMemoId(int memoId) {
		this.memoId = memoId;
	}

	public String getAulKey() {
		return aulKey;
	}

	public void setAulKey(String aulKey) {
		this.aulKey = aulKey;
	}

	public String getAulId() {
		return aulId;
	}

	public void setAulId(String aulId) {
		this.aulId = aulId;
	}

	public String getAulDatetime() {
		return aulDatetime;
	}

	public void setAulDatetime(String aulDatetime) {
		this.aulDatetime = aulDatetime;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", blogId=" + blogId + ", memoId=" + memoId + ", aulKey=" + aulKey + ", aulId="
				+ aulId + ", aulDatetime=" + aulDatetime + "]";
	}

}
