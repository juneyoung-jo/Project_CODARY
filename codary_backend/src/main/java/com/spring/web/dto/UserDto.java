package com.spring.web.dto;

import java.sql.Timestamp;

public class UserDto {

	private String uid;
	private String blogId;
	private String memoId;
	private String provider; //google, kakao, naver
	private String providerId;
	private Timestamp createDate;

	public UserDto() {}
	public UserDto(String uid, String blogId, String memoId, String provider, String providerId) {
		this.uid = uid;
		this.blogId = blogId;
		this.memoId = memoId;
		this.provider = provider;
		this.providerId = providerId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getMemoId() {
		return memoId;
	}
	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	

}
