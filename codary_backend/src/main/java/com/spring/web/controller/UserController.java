package com.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.BlogDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;
import com.spring.web.service.JwtServiceImpl;
import com.spring.web.service.UserService;
import com.spring.web.service.oauth.GoogleOauthService;
import com.spring.web.service.oauth.KakaoOauthService;
import com.spring.web.service.oauth.NaverOauthService;
import com.spring.web.service.oauth.SocialOauth;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private KakaoOauthService kakaoOauthService;
	@Autowired
	private NaverOauthService naverOauthService;
	@Autowired
	private GoogleOauthService googleOauthService;
	@Autowired
	private JwtServiceImpl jwtService;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@PostMapping("/login/{socialLoginType}")
	public ResponseEntity<Map<String, Object>> access(
			@PathVariable("socialLoginType") String socialLoginType,
			@RequestBody String accessTocken){
		System.out.println("#"+socialLoginType+"로그인 요청됨!!");
		System.out.println("# accessTocken: "+ accessTocken);
		SocialOauth socialOauth = getSocialOauth(socialLoginType);
		
		HashMap<String, Object> userInfo = socialOauth.getUserInfoFromOauth(accessTocken);
		logger.info("#Get userInfo: {}", userInfo);

		UserDto user = null;
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			user = userService.findByProvider(userInfo);
			resultMap.put("message", SUCCESS);
			if (user == null) {
				logger.info("#최초 로그인입니다.");
				resultMap = userService.save(userInfo);
			} else {
				logger.info("#기존회원입니다.");
				resultMap.put("user", user);
				resultMap.put("userInfo", userService.findUserInfoById(user.getUid()));
				resultMap.put("blog", userService.findBlogById(user.getBlogId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
		}
		user = (UserDto) resultMap.get("user");

//		String token = jwtService.create("uId", user.getUid(), "access-token");
		String token  =  jwtService.create((UserDto)resultMap.get("user"), (UserInfoDto)resultMap.get("userInfo"), (BlogDto)resultMap.get("blog"), "access-token");
		logger.debug("#토큰정보: " + token);
		resultMap.put("access-token", token);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/jwt")
	public String jwt(String jwt) {
//		jwtService.parseAccessToken(jwt);
		return "";
	}
	
	public SocialOauth getSocialOauth(String socialLoginType) {
		switch (socialLoginType) {
		case "kakao":
			return kakaoOauthService;
		case "naver":
			return naverOauthService;
		case "google":
			return googleOauthService;
		default:
			return null;
		}
	}

}
