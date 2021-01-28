package com.spring.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserInfoDto : 유저 상세정보", description = "UserInfoDto 회원 개인정보를 나타낸다.")
public class UserInfoDto {

	@ApiModelProperty(value = "회원 고유번호 13자리")
	private String uid;
	@ApiModelProperty(value = "회원명")
	private String nickname;
	@ApiModelProperty(value = "프로필 사진")
	private String profile;
	
	public UserInfoDto() {
	}
	public UserInfoDto(String uid, String nickname, String profile) {
		this.uid = uid;
		this.nickname = nickname;
		this.profile = profile;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	@Override
	public String toString() {
		return "UserInfoDto [uid=" + uid + ", nickname=" + nickname + ", profile=" + profile + "]";
	}

}
