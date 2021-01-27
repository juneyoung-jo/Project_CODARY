package com.spring.web.dto;

public class CommentCountDto {

	private int blogContentId;

	private int count;

	public int getBlogContentId() {
		return blogContentId;
	}

	public void setBlogContentId(int blogContentId) {
		this.blogContentId = blogContentId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public CommentCountDto(int blogContentId, int count) {
		super();
		this.blogContentId = blogContentId;
		this.count = count;
	}

	@Override
	public String toString() {
		return "CommentCountDto [blogContentId=" + blogContentId + ", count=" + count + "]";
	}

}
