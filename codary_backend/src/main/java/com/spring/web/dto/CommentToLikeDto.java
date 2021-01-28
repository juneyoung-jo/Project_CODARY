package com.spring.web.dto;

public class CommentToLikeDto {

	private String uid;
	private int commentNum;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	@Override
	public String toString() {
		return "CommentToLikeDto [uid=" + uid + ", commentNum=" + commentNum + "]";
	}

}
