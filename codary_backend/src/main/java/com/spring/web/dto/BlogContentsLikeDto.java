package com.spring.web.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "BlogContentstLikeDto : 블로그 글 좋아요", description = "블로그 글에 대한 좋아요 정보")
public class BlogContentsLikeDto {

	private String uid;
	private int blogContentsId;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getBlogContentsId() {
		return blogContentsId;
	}

	public void setBlogContentsId(int blogContentsId) {
		this.blogContentsId = blogContentsId;
	}

	@Override
	public String toString() {
		return "BlogContentsLikeDto [uid=" + uid + ", blogContentsId=" + blogContentsId + "]";
	}

}
