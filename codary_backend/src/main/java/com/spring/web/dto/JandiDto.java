package com.spring.web.dto;

import java.util.Date;

public class JandiDto {
	
	private int jandInum;
	private String blogId;
	private String blogDatetime;
	private Integer jandiCnt;
	
	public int getJandInum() {
		return jandInum;
	}
	public void setJandInum(int jandInum) {
		this.jandInum = jandInum;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getBlogDatetime() {
		return blogDatetime;
	}
	public void setBlogDatetime(String blogDatetime) {
		this.blogDatetime = blogDatetime;
	}
	public Integer getJandiCnt() {
		return jandiCnt;
	}
	public void setJandiCnt(Integer jandiCnt) {
		this.jandiCnt = jandiCnt;
	}
	@Override
	public String toString() {
		return "JandiDto [jandInum=" + jandInum + ", blogId=" + blogId + ", blogDatetime=" + blogDatetime
				+ ", jandiCnt=" + jandiCnt + "]";
	}
	
	
	
	
}
