package com.spring.web.dto;

public class HashtagDto {

	private int hashtagId;
	private String hashtagContent;

	public HashtagDto() {
		// TODO Auto-generated constructor stub
	}
	
	public HashtagDto(int hashtagId, String hashtagContent) {
		this.hashtagId = hashtagId;
		this.hashtagContent = hashtagContent;
	}

	public int getHashtagId() {
		return hashtagId;
	}

	public void setHashtagId(int hashtagId) {
		this.hashtagId = hashtagId;
	}
	public int getHashtag_id() {
		return hashtagId;
	}
	
	public void setHashtag_id(int hashtagId) {
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
