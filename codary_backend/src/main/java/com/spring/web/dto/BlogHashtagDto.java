package com.spring.web.dto;

public class BlogHashtagDto {

	private int hashtagId;
	private int blogContentsId;
	private String blogId;

	public BlogHashtagDto() {
	}

	public BlogHashtagDto(int hashtagId, int blogContentsId, String blogId) {
		this.hashtagId = hashtagId;
		this.blogContentsId = blogContentsId;
		this.blogId = blogId;
	}

	public int getHashtagId() {
		return hashtagId;
	}

	public void setHashtagId(int hashtagId) {
		this.hashtagId = hashtagId;
	}

	public int getBlogContentsId() {
		return blogContentsId;
	}

	public void setBlogContentsId(int blogContentsId) {
		this.blogContentsId = blogContentsId;
	}

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "BlogHashtagDto [hashtagId=" + hashtagId + ", blogContentsId=" + blogContentsId + ", blogId=" + blogId
				+ "]";
	}

}
