package com.spring.web.service.oauth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public interface SocialOauth {

	final String KAKAO_CLIENT_ID = "cc55fbaa2ba8ee734547019f8cba7abf";
	final String KAKAO_REDIRECT_URI = "http://localhost:8000/codary/login/kakao";
	final String KAKAO_REQUEST_URL = "https://kauth.kakao.com/oauth/token";
//	final String KAKAO_CLIENT_ID = "621d1c02ff46c2f3b8b95949020e0886";
//	final String KAKAO_REDIRECT_URI = "http://i4c105.p.ssafy.io:8000/codary/login/kakao";

	final String NAVER_CLIENT_ID = "WpeeiuJ12wUmEB1bn6hr";
	final String NAVER_CLIENT_SECRET = "L2NhJ5foO3";
	final String NAVER_REDIRECT_URI = "http://localhost:8000/codary/login/naver";
	final String NAVER_REQUEST_URL = "https://nid.naver.com/oauth2.0/token";
	
	final String GOOGLE_CLIENT_ID = "509903905678-vkgf01saln1b88fti0cd3vlivbkcd00g.apps.googleusercontent.com";
	final String GOOGLE_CLIENT_SECRET = "tfL3NYfxN8mFKwAQa5BJDLqw";
	final String GOOGLE_REDIRECT_URI = "http://localhost:8000/codary/login/google";
	final String GOOGLE_REQUEST_URL = "https://oauth2.googleapis.com/token";

	
	final String DEFAULT_IMG_PATH = "###### DEFAULT_IMAGE #####";
	public static final Logger logger = LoggerFactory.getLogger(SocialOauth.class);

	public default String getAccessToken(String socialLoginType, String authorizeCode) {
		String access_Token = "";
		String refresh_Token = "";
		Map<String, String> social = getSocialType(socialLoginType);

		try {
			URL url = new URL(social.get("reqURL"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=" + social.get("client_id"));
			sb.append("&redirect_uri=" + social.get("redirect_uri"));
			sb.append("&code=" + authorizeCode);
			if (socialLoginType.equals("naver")) {
				sb.append("&client_secret=" + NAVER_CLIENT_SECRET);
			}else if(socialLoginType.equals("google")) {
				sb.append("&client_secret=" + GOOGLE_CLIENT_SECRET);
			}
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			logger.info("#responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			logger.info("#response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			if(socialLoginType.equals("google")) {
				access_Token = element.getAsJsonObject().get("id_token").getAsString();
			}else {
				access_Token = element.getAsJsonObject().get("access_token").getAsString();
			}
//			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			// System.out.println("access_token : " + access_Token);
			// System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	public default Map<String, String> getSocialType(String socialLoginType) {
		Map<String, String> resultMap = new HashMap<String, String>();
		switch (socialLoginType) {
		case "kakao":
			resultMap.put("reqURL", KAKAO_REQUEST_URL);
			resultMap.put("client_id", KAKAO_CLIENT_ID);
			resultMap.put("redirect_uri", KAKAO_REDIRECT_URI);
			return resultMap;
		case "naver":
			resultMap.put("reqURL", NAVER_REQUEST_URL);
			resultMap.put("client_id", NAVER_CLIENT_ID);
			resultMap.put("redirect_uri", NAVER_REDIRECT_URI);
			return resultMap;
			
		case "google":
			resultMap.put("reqURL", GOOGLE_REQUEST_URL);
			resultMap.put("client_id", GOOGLE_CLIENT_ID);
			resultMap.put("redirect_uri", GOOGLE_REDIRECT_URI);
			return resultMap;
		default:
			return null;
		}
	}

	public HashMap<String, Object> getUserInfoFromOauth(String accessToken);

}
