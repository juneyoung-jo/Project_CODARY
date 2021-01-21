package com.spring.web.dto;

public class BlogDto {

	private int blogId;
	private int blogLike;
	private String blogProfile;
	private int blogView;

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public int getBlogLike() {
		return blogLike;
	}

	public void setBlogLike(int blogLike) {
		this.blogLike = blogLike;
	}

	public String getBlogProfile() {
		return blogProfile;
	}

	public void setBlogProfile(String blogProfile) {
		this.blogProfile = blogProfile;
	}

	public int getBlogView() {
		return blogView;
	}

	public void setBlogView(int blogView) {
		this.blogView = blogView;
	}

	@Override
	public String toString() {
		return "BlogDto [blogId=" + blogId + ", blogLike=" + blogLike + ", blogProfile=" + blogProfile + ", blogView="
				+ blogView + "]";
	}

}
