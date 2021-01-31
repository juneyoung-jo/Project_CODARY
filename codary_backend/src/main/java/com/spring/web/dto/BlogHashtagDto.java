package com.spring.web.dto;

public class BlogHashtagDto {

	private int hashtagId;
	private int blogContentsId;
	private int blogId;

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

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	@Override
	public String toString() {
		return "BlogHashtagDto [hashtagId=" + hashtagId + ", blogContentsId=" + blogContentsId + ", blogId=" + blogId
				+ "]";
	}

}
