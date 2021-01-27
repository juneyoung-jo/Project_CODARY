package com.spring.web.dto;

public class UserInfoDto {

	private String uid;
	private String nickname;
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
