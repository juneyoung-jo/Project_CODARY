package com.spring.web.dto;

import java.util.List;

public class TestDto {

	private int blogContentsId;
	private String hashtagContent;

	public int getBlogContentsId() {
		return blogContentsId;
	}

	public void setBlogContentsId(int blogContentsId) {
		this.blogContentsId = blogContentsId;
	}

	public String getHashtagContent() {
		return hashtagContent;
	}

	public void setHashtagContent(String hashtagContent) {
		this.hashtagContent = hashtagContent;
	}

	@Override
	public String toString() {
		return "TestDto [blogContentsId=" + blogContentsId + ", hashtagContent=" + hashtagContent + "]";
	}

}
