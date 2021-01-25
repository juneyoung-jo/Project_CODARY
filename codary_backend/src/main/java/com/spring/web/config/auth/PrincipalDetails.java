package com.spring.web.config.auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.spring.web.dto.UserDto;


public class PrincipalDetails implements UserDetails, OAuth2User{

	private UserDto user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(UserDto user) {
		this.user = user;
	}
	
	public PrincipalDetails(UserDto userEntity, Map<String, Object> attributes) {
		this.user = userEntity;
		this.attributes = attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return "password";
	}

	@Override
	public String getUsername() {
		return user.getUid();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 우리 사이트에서 1년동안 회원이 로그인을 안하면 휴먼 계정으로 하기로 함.
		// 현재시간 - 로그인 시간 => 1년을 초과하면 return false;
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
