package com.spring.web.dto;

public class CommentDto {

	private int commentNum;
	private int blogContentsId;
	private int blogId;
	private int commentLike;
	private String commentContent;
	private String uid;
	private String commentDatetime;

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
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

	public int getCommentLike() {
		return commentLike;
	}

	public void setCommentLike(int commentLike) {
		this.commentLike = commentLike;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCommentDatetime() {
		return commentDatetime;
	}

	public void setCommentDatetime(String commentDatetime) {
		this.commentDatetime = commentDatetime;
	}

	@Override
	public String toString() {
		return "CommentDto [commentNum=" + commentNum + ", blogContentsId=" + blogContentsId + ", blogId=" + blogId
				+ ", commentLike=" + commentLike + ", commentContent=" + commentContent + ", uid=" + uid
				+ ", commentDatetime=" + commentDatetime + "]";
	}

}
