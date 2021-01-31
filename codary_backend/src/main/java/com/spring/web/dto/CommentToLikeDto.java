package com.spring.web.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "CommentToLike : 댓글 좋아요 정보",description = "댓글 좋아요 정보를 나타냄")
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
