package com.spring.web.dto;

public class BlogDto {

	private int blogId;
	private int blogLike;
	private String blogCover;
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

	public String getBlogCover() {
		return blogCover;
	}

	public void setBlogCover(String blogProfile) {
		this.blogCover = blogProfile;
	}

	public int getBlogView() {
		return blogView;
	}

	public void setBlogView(int blogView) {
		this.blogView = blogView;
	}

	@Override
	public String toString() {
		return "BlogDto [blogId=" + blogId + ", blogLike=" + blogLike + ", blogCover=" + blogCover + ", blogView="
				+ blogView + "]";
	}

}
