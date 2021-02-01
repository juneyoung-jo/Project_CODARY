package com.spring.web.dto;

public class UsergraphDto {
	
	private String blogId;
	private String usergraphDate;
	private int blogView;
	
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getUsergraphDate() {
		return usergraphDate;
	}
	public void setUsergraphDate(String usergraphDate) {
		this.usergraphDate = usergraphDate;
	}
	public int getBlogView() {
		return blogView;
	}
	public void setBlogView(int blogView) {
		this.blogView = blogView;
	}
	
	@Override
	public String toString() {
		return "UsergraphDto [blogId=" + blogId + ", usergraphDate=" + usergraphDate
				+ ", blogView=" + blogView + "]";
	}
	
	
}
