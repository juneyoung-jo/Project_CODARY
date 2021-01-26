package com.spring.web.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.UserDto;
import com.spring.web.service.KakaoOauthService;
import com.spring.web.service.UserService;

@RestController
public class UserController {

	@Autowired private UserService userService;
	@Autowired private KakaoOauthService kakaoOauthService;
	
	@GetMapping("/login/kakao")
	public String login(@RequestParam("code") String code) {
		System.out.println("#Log - KakaoApi 로그인 ");
		String accssTocken = kakaoOauthService.getAccessToken(code);
		System.out.println("#Log - Get userInfo");
		HashMap<String, Object> userInfo = kakaoOauthService.getUserInfoFromOauth(accssTocken);

		System.out.println((String) userInfo.get("nickname"));
		UserDto user = userService.findById((String) userInfo.get("nickname"));
		if(user == null) {
			System.out.println("#Log - 최초 로그인입니다.");
		}else {
			System.out.println("#Log - 기존회원입니다.");
		}
		// 토큰생성
		
		return "index";
	}
	
	
}
