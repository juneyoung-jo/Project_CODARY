package com.spring.web.service;

import java.util.HashMap;


public interface OAuthService {

	public String getAccessToken(String authorizeCode) ;
	public HashMap<String, Object> getUserInfoFromOauth (String accessToken);
	
}
