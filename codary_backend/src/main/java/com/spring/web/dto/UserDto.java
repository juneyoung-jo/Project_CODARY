package com.spring.web.dto;

import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserDto : 유저 정보", description = "User 테이블 정보를 나타낸다.")
public class UserDto {
	@ApiModelProperty(value = "회원 고유번호 13자리")
	private String uid;
	@ApiModelProperty(value = "회원에게 할당된 블로그 고유번호 13자리")
	private String blogId;
	@ApiModelProperty(value = "메모장 고유번호 13자리")
	private String memoId;
	@ApiModelProperty(value = "회원가입시 정보 제공 서비스명 (google, kakao, naver")
	private String provider; //google, kakao, naver
	@ApiModelProperty(value = "정보 제공 서비스 내 회원 고유번호")
	private String providerId;
	@ApiModelProperty(value = "회원가입 일시")
	private Timestamp createDate;

	public UserDto() {}
	public UserDto(String uid, String blogId, String memoId, String provider, String providerId) {
		this.uid = uid;
		this.blogId = blogId;
		this.memoId = memoId;
		this.provider = provider;
		this.providerId = providerId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getMemoId() {
		return memoId;
	}
	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	

}
