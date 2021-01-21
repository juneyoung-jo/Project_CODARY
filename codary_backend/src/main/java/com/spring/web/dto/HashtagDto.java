package com.spring.web.dto;

public class HashtagDto {

	private int hashtagId;
	private String hashtagContent;

	public int getHashtagId() {
		return hashtagId;
	}

	public void setHashtagId(int hashtagId) {
		this.hashtagId = hashtagId;
	}

	public String getHashtagContent() {
		return hashtagContent;
	}

	public void setHashtagContent(String hashtagContent) {
		this.hashtagContent = hashtagContent;
	}

	@Override
	public String toString() {
		return "HashtagDto [hashtagId=" + hashtagId + ", hashtagContent=" + hashtagContent + "]";
	}

}
