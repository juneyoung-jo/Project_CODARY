package com.spring.web.dto;

public class BlogContentsDto {

	private int blogContentsId;
	private int blogId;
	private String blogContents;
	private int blogContentsView;
	private int blogContentsLike;
	private String blogDatetime;

	public int getBlogContentsId() {
		return blogContentsId;
	}

	public void setBlogContentsId(int blogContentsId) {
		this.blogContentsId = blogContentsId;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogContents() {
		return blogContents;
	}

	public void setBlogContents(String blogContents) {
		this.blogContents = blogContents;
	}

	public int getBlogContentsView() {
		return blogContentsView;
	}

	public void setBlogContentsView(int blogContentsView) {
		this.blogContentsView = blogContentsView;
	}

	public int getBlogContentsLike() {
		return blogContentsLike;
	}

	public void setBlogContentsLike(int blogContentsLike) {
		this.blogContentsLike = blogContentsLike;
	}

	public String getBlogDatetime() {
		return blogDatetime;
	}

	public void setBlogDatetime(String blogDatetime) {
		this.blogDatetime = blogDatetime;
	}

	@Override
	public String toString() {
		return "BlogContentsDto [blogContentsId=" + blogContentsId + ", blogId=" + blogId + ", blogContents="
				+ blogContents + ", blogContentsView=" + blogContentsView + ", blogContentsLike=" + blogContentsLike
				+ ", blogDatetime=" + blogDatetime + "]";
	}

}
