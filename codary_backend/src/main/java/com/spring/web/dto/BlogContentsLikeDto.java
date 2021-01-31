package com.spring.web.dto;

public class BlogContentsLikeDto {

	private int num;
	private String uid;
	private int blogContentsId;

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

	public int getBlogContentsId() {
		return blogContentsId;
	}

	public void setBlogContentsId(int blogContentsId) {
		this.blogContentsId = blogContentsId;
	}

	@Override
	public String toString() {
		return "BlogContentsLikeDto [num=" + num + ", uid=" + uid + ", blogContentsId=" + blogContentsId + "]";
	}

}
