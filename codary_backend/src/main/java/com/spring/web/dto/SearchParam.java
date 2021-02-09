package com.spring.web.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "SearchParam : 검색시 파라미터", description = "검색시 필요한 파라미터 객체")
public class SearchParam {

	private String keyword;
	private int start;
	private int spp;
	
	public SearchParam() {
	}

	public SearchParam(String keyword, int start, int spp) {
		super();
		this.keyword = keyword;
		this.start = start;
		this.spp = spp;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSpp() {
		return spp;
	}

	public void setSpp(int spp) {
		this.spp = spp;
	}

	@Override
	public String toString() {
		return "SearchParam [keyword=" + keyword + ", start=" + start + ", spp=" + spp + "]";
	}
	
}
