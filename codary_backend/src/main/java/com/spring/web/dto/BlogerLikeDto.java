package com.spring.web.dto;

public class BlogerLikeDto {

	private String uid;
	private int blogId;

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

	@Override
	public String toString() {
		return "BlogerLikeDto [uid=" + uid + ", blogId=" + blogId + "]";
	}

}
