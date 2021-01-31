package com.spring.web.dto;

public class BlogerLikeDto {

	private int num;
	private String uid;
	private int blogId;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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

	@Override
	public String toString() {
		return "BlogerLikeDto [num=" + num + ", uid=" + uid + ", blogId=" + blogId + "]";
	}

}
