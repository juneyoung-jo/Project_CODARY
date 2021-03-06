package com.spring.web.dto;

public class LoginCallBackDto {

	private String uid;
	private String memoId;
	private String blogId;
	private String nickname;
	private String profile;
	private String provider;
	public LoginCallBackDto() {
	}
	
	public LoginCallBackDto(String uid, String memoId, String blogId, String nickname, String profile,
			String provider) {
		this.uid = uid;
		this.memoId = memoId;
		this.blogId = blogId;
		this.nickname = nickname;
		this.profile = profile;
		this.provider = provider;
	}


	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getMemoId() {
		return memoId;
	}
	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
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
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	@Override
	public String toString() {
		return "LoginCallBackDto [uid=" + uid + ", memoId=" + memoId + ", blogId=" + blogId + ", nickname=" + nickname
				+ ", profile=" + profile + ", provider=" + provider + "]";
	}
}
