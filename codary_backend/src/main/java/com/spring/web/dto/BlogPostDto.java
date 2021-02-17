package com.spring.web.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BlogPostDto : 블로그 글 목록", description = "BlogPostDto 글목록을 최신순으로 정렬")
public class BlogPostDto {
	@ApiModelProperty(value = "블로그 번호")
	private String blogId;
	@ApiModelProperty(value = "글번호")
	private int blogContentsId;
	@ApiModelProperty(value = "글 제목")
	private String blogContentsTitle;
	@ApiModelProperty(value = "글 내용")
	private String blogContents;
	@ApiModelProperty(value = "조회수")
	private int blogContentsView;
	@ApiModelProperty(value = "좋아요")
	private int blogContentsLike;
	@ApiModelProperty(value = "글 작성일")
	private String blogDatetime;
	@ApiModelProperty(value = "글 커버 이미지")
	private String blogContentsCover;
	@ApiModelProperty(value = "작성자 닉네임")
	private String nickname;
	@ApiModelProperty(value = "작성자 프로필 이미지")
	private String profile;
	@ApiModelProperty(value = "댓글 갯수")
	private int commentCnt;
	@ApiModelProperty(value = "해시태그")
	private List<HashtagDto> hashtags;
	
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public int getBlogContentsId() {
		return blogContentsId;
	}
	public void setBlogContentsId(int blogContentsId) {
		this.blogContentsId = blogContentsId;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public int getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	public List<HashtagDto> getHashtags() {
		return hashtags;
	}
	public void setHashtags(List<HashtagDto> hashtags) {
		this.hashtags = hashtags;
	}
	@Override
	public String toString() {
		return "BlogPostDto [blogId=" + blogId + ", blogContentsId=" + blogContentsId + ", blogContentsTitle="
				+ blogContentsTitle + ", blogContents=" + blogContents + ", blogContentsView=" + blogContentsView
				+ ", blogContentsLike=" + blogContentsLike + ", blogDatetime=" + blogDatetime + ", blogContentsCover="
				+ blogContentsCover + ", nickname=" + nickname + ", profile=" + profile + ", commentCnt=" + commentCnt
				+ ", hashtags=" + hashtags + "]";
	}
	
	
}
/*

select bc.blog_id, bc.blog_contents_id, bc.blog_contents_title,
		bc.blog_contents, bc.blog_contents_view, bc.blog_contents_like, bc.blog_datetime,
        bc.blog_contents_cover, info.nickname, info.profile
from blogcontents bc inner join user u
on bc.blog_id = u.blog_id 
inner join userinfo info
on u.uid = info.uid
where bc.isdeleted = 0 and u.isdeleted = 0
order by blog_contents_id DESC;

 */