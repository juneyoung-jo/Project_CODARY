package com.spring.web.dto;

public class Testdb {

	private String name;
	private String pw;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	@Override
	public String toString() {
		return "Testdb [name=" + name + ", pw=" + pw + "]";
	}
	
	
}
