package com.spring.web.service.oauth;

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
public class NaverOauthService implements SocialOauth {

	public static final Logger logger = LoggerFactory.getLogger(NaverOauthService.class);

	// userinfo가져오기
	public HashMap<String, Object> getUserInfoFromOauth(String accessToken) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

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

			JsonObject response = element.getAsJsonObject().get("response").getAsJsonObject();

			// 인증수단, 인증 서버내 pk값, 이메일, 이름, 프로필 사진
			String provider = "Naver";
			String providerId = response.getAsJsonObject().get("id").getAsString();
			String profileImg = response.getAsJsonObject().get("profile_image").getAsString();
			String nickname = response.getAsJsonObject().get("name").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("profileImg", profileImg);
			userInfo.put("provider", provider);
			userInfo.put("providerId", providerId);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}

}
