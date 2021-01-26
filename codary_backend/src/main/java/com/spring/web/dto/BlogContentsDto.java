package com.spring.web.dto;

public class BlogContentsDto {

	private int blogContentsId;
	private int blogId;
	private String blogContentsTitle;
	private String blogContents;
	private int blogContentsView;
	private int blogContentsLike;
	private String blogDatetime;
	private String blogContentsCover;
	
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
	public String getBlogContentsTitle() {
		return blogContentsTitle;
	}
	public void setBlogContentsTitle(String blogContentsTitle) {
		this.blogContentsTitle = blogContentsTitle;
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
	public String getBlogContentsCover() {
		return blogContentsCover;
	}
	public void setBlogContentsCover(String blogCover) {
		this.blogContentsCover = blogCover;
	}
	
	@Override
	public String toString() {
		return "BlogContentsDto [blogContentsId=" + blogContentsId + ", blogId=" + blogId + ", blogContentsTitle="
				+ blogContentsTitle + ", blogContents=" + blogContents + ", blogContentsView=" + blogContentsView
				+ ", blogContentsLike=" + blogContentsLike + ", blogDatetime=" + blogDatetime + ", blogContentsCover="
				+ blogContentsCover + "]";
	}

}
