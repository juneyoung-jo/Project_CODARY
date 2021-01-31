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
public class KakaoOauthService implements SocialOauth {

	public static final Logger logger = LoggerFactory.getLogger(KakaoOauthService.class);

	public HashMap<String, Object> getUserInfoFromOauth(String accessToken) {

		final String DEFAULT_IMG_PATH = "###### DEFAULT_IMAGE #####";

		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
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

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			JsonObject profile = kakao_account.getAsJsonObject().get("profile").getAsJsonObject();

			// 인증수단, 인증 서버내 pk값, 이메일, 이름, 프로필 사진
			String provider = "Kakao";
			String providerId = element.getAsJsonObject().get("id").getAsString();
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String profileImg = DEFAULT_IMG_PATH;

			if (profile.getAsJsonObject().get("profile_image_url") != null) {
				profileImg = profile.getAsJsonObject().get("profile_image_url").getAsString();
			}

			userInfo.put("nickname", nickname);
			userInfo.put("profileImg", profileImg);
			userInfo.put("provider", provider);
			userInfo.put("providerId", providerId);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return userInfo;
	}
}
