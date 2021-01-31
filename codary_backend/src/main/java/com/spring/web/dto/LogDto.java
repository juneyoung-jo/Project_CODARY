package com.spring.web.dto;

public class LogDto {

	private String datetime;
	private String uid;
	private int blogContentsId;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getBlogContentsId() {
		return blogContentsId;
	}

	public void setBlogContentsId(int blogContentsId) {
		this.blogContentsId = blogContentsId;
	}

	@Override
	public String toString() {
		return "LogDto [datetime=" + datetime + ", uid=" + uid + ", blogContentsId=" + blogContentsId + "]";
	}

}
