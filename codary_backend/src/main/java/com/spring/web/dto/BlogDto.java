package com.spring.web.dto;

public class BlogDto {

	private String blogId;
	private int blogLike;
	private String blogCover;
	private int blogView;
	private String blogTitle;

	public BlogDto() {
	}

	public BlogDto(String blogId, int blogLike, String blogTitle, String blogCover, int blogView) {
		super();
		this.blogId = blogId;
		this.blogLike = blogLike;
		this.blogTitle = blogTitle;
		this.blogCover = blogCover;
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

	public String getBlogCover() {
		return blogCover;
	}

	public void setBlogCover(String blogCover) {
		this.blogCover = blogCover;
	}

	public int getBlogView() {
		return blogView;
	}

	public void setBlogView(int blogView) {
		this.blogView = blogView;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	@Override
	public String toString() {
		return "BlogDto [blogId=" + blogId + ", blogLike=" + blogLike + ", blogCover=" + blogCover + ", blogView="
				+ blogView + ", blogTitle=" + blogTitle + "]";
	}


}
