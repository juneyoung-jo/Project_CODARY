package com.spring.web.dto;

public class BlogerLikeDto {

	private String uid;
	private String blogId;

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

	@Override
	public String toString() {
		return "BlogerLikeDto [uid=" + uid + ", blogId=" + blogId + "]";
	}

}
