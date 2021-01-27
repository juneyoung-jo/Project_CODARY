package com.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.UserDto;
import com.spring.web.service.JwtServiceImpl;
import com.spring.web.service.KakaoOauthService;
import com.spring.web.service.UserService;

@RestController
public class UserController {

	@Autowired private UserService userService;
	@Autowired private KakaoOauthService kakaoOauthService;
	@Autowired private JwtServiceImpl jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@GetMapping("/login/kakao")
	public ResponseEntity<Map<String, Object>> login(@RequestParam("code") String code) {
		System.out.println("#Log - KakaoApi 로그인 ");
		String accssTocken = kakaoOauthService.getAccessToken(code);
		System.out.println("#Log - Get userInfo");
		HashMap<String, Object> userInfo = kakaoOauthService.getUserInfoFromOauth(accssTocken);

		UserDto user = userService.findByProvider(userInfo);
		if(user == null) {
			System.out.println("#Log - 최초 로그인입니다.");
			user = userService.save(userInfo);
		}else {
			System.out.println("#Log - 기존회원입니다.");
			System.out.println("uId: "+ user.getUid());
		}
		
		String token  = jwtService.create("uId", user.getUid(), "access-token");
		System.out.println("#Log - 토큰정보: "+ token);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("access-token", token);
		resultMap.put("uId", user.getUid());
		resultMap.put("message", SUCCESS);
		
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
	}
	
	
}
