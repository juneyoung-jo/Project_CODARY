package com.spring.web.dto;

public class BlogDto {

	private String blogId;
	private int blogLike;
	private String blogTitle;
	private String blogProfile;
	private int blogView;

	public BlogDto() {
	}

	public BlogDto(String blogId, int blogLike, String blogTitle, String blogProfile, int blogView) {
		super();
		this.blogId = blogId;
		this.blogLike = blogLike;
		this.blogTitle = blogTitle;
		this.blogProfile = blogProfile;
		this.blogView = blogView;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public int getBlogLike() {
		return blogLike;
	}

	public void setBlogLike(int blogLike) {
		this.blogLike = blogLike;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
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
		return "BlogDto [blogId=" + blogId + ", blogLike=" + blogLike + ", blogTitle=" + blogTitle + ", blogProfile="
				+ blogProfile + ", blogView=" + blogView + "]";
	}
	

}
