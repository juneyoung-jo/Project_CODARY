package com.spring.web.dto;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "BlogContentstDto : 블로그 글", description = "블로그에 작성한 글에 대한 정보")
public class BlogContentsDto {

	private int blogContentsId;
	private String blogId;
	private String blogContentsTitle;
	private String blogContents;
	private int blogContentsView;
	private int blogContentsLike;
	private String blogDatetime;
	private String blogContentsCover;
	private byte isdeleted;
	private List<Map<String, String>> hashTag;
	
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
	
	public void setBlogContentsCover(String blogContentsCover) {
		this.blogContentsCover = blogContentsCover;
	}
	
	public byte getIsDeleted() {
		return isdeleted;
	}
	
	public void setIsDeleted(byte isDeleted) {
		this.isdeleted = isDeleted;
	}
	
	
	public byte getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(byte isdeleted) {
		this.isdeleted = isdeleted;
	}

	public List<Map<String, String>> getHashTag() {
		return hashTag;
	}

	public void setHashTag(List<Map<String, String>> hashTag) {
		this.hashTag = hashTag;
	}

	@Override
	public String toString() {
		return "BlogContentsDto [blogContentsId=" + blogContentsId + ", blogId=" + blogId + ", blogContentsTitle="
				+ blogContentsTitle + ", blogContents=" + blogContents + ", blogContentsView=" + blogContentsView
				+ ", blogContentsLike=" + blogContentsLike + ", blogDatetime=" + blogDatetime + ", blogContentsCover="
				+ blogContentsCover + ", isdeleted=" + isdeleted + ", hashTag=" + hashTag + "]";
	}

	
	
}
