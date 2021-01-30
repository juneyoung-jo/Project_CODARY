package com.spring.web.service.oauth;

import java.util.HashMap;

public interface OAuthService {

	final String KAKAO_CLIENT_ID = "cc55fbaa2ba8ee734547019f8cba7abf";
	final String KAKAO_REDIRECT_URI = "http://localhost:8000/codary/login/kakao";
//	final String KAKAO_CLIENT_ID = "621d1c02ff46c2f3b8b95949020e0886";
//	final String KAKAO_REDIRECT_URI = "http://i4c105.p.ssafy.io:8000/codary/login/kakao";

	final String NAVER_CLIENT_ID = "WpeeiuJ12wUmEB1bn6hr";
	final String NAVER_CLIENT_SECRET = "L2NhJ5foO3";
	final String NAVER_REDIRECT_URI = "http://localhost:8000/codary/login/naver";
	final String NAVER_REQUEST_URL = "https://nid.naver.com/oauth2.0/token";

	public String getAccessToken(String authorizeCode);

	public HashMap<String, Object> getUserInfoFromOauth(String accessToken);

}
