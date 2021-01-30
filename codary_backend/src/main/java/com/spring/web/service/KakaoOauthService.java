package com.spring.web.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class KakaoOauthService implements OAuthService {

	public static final Logger logger = LoggerFactory.getLogger(KakaoOauthService.class);
	final String CLIENT_ID = "cc55fbaa2ba8ee734547019f8cba7abf";
	final String REDIRECT_URI = "http://localhost:8000/codary/login/kakao";
	
//	final String DEPLOY_CLIENT_ID = "621d1c02ff46c2f3b8b95949020e0886";
//	final String DEPLOY_REDIRECT_URI = "http://i4c105.p.ssafy.io:8000/codary/login/kakao";
	

	public String getAccessToken(String authorize_code) {

		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=" + CLIENT_ID);
			sb.append("&redirect_uri=" + REDIRECT_URI);
			sb.append("&code=" + authorize_code);
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

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			// System.out.println("access_token : " + access_Token);
			// System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	public HashMap<String, Object> getUserInfoFromOauth(String access_Token) {
		
		final String DEFAULT_IMG_PATH = "###### DEFAULT_IMAGE #####"; 
		
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			logger.info("#responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			logger.info("#response body : " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			JsonObject profile = kakao_account.getAsJsonObject().get("profile").getAsJsonObject();

			// 인증수단, 인증 서버내 pk값, 이메일, 이름, 프로필 사진
			String provider = "Kakao";
			String providerId = element.getAsJsonObject().get("id").getAsString();
			String email = "kakao_" + element.getAsJsonObject().get("id").getAsString();
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String profileImg = DEFAULT_IMG_PATH; 
			
			if(profile.getAsJsonObject().get("profile_image_url") != null) {
				profileImg = profile.getAsJsonObject().get("profile_image_url").getAsString();
			}

			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
			userInfo.put("profileImg", profileImg);
			userInfo.put("provider", provider);
			userInfo.put("providerId", providerId);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return userInfo;
	}
}
