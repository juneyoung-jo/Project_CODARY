package com.spring.web.service.oauth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spring.web.service.JwtServiceImpl;

@Service
public class GoogleOauthService implements SocialOauth {

	@Autowired
	private JwtServiceImpl jwtServiceImpl;

	public static final Logger logger = LoggerFactory.getLogger(GoogleOauthService.class);

	// userinfo가져오기
	public HashMap<String, Object> getUserInfoFromOauth(String accessToken) {

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token="+accessToken;
//		System.out.println(reqURL);
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

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

			// 인증수단, 인증 서버내 pk값, 이메일, 이름, 프로필 사진
			String provider = "google";
			String providerId = element.getAsJsonObject().get("sub").getAsString();
			String nickname = element.getAsJsonObject().get("name").getAsString();
			String profileImg = element.getAsJsonObject().get("picture").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("profileImg", profileImg);
			userInfo.put("provider", provider);
			userInfo.put("providerId", providerId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userInfo;
	}

}
