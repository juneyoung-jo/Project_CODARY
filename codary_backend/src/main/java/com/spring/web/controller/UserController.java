package com.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.spring.web.service.UserService;
import com.spring.web.service.oauth.KakaoOauthService;
import com.spring.web.service.oauth.NaverOauthService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private KakaoOauthService kakaoOauthService;
	@Autowired
	private NaverOauthService naverOauthService;
	@Autowired
	private JwtServiceImpl jwtService;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	/**
	 * 카카오 아이디 로그인
	 * 
	 * @param - (카카오 서버에서 응답)
	 * @return UserDto, BlogDto, UserInfo, message
	 */
	@ApiOperation(value = "카카오 아이디로 로그인 하기", notes = " 카카오 아이디로 로그인시, 해당 회원 정보를 리턴한다.<br> 최초 로그인인 경우 회원가입이 진행된 후 가입정보를 리턴한다.<br> "
			+ "@param: UserDto, BlogDto, UserInfoDto, message")
	@GetMapping("/login/kakao")
	public ResponseEntity<Map<String, Object>> loginByKakao(@RequestParam("code") String code) {
		logger.info("#KakaoApi 로그인 ");
		String accessTocken = kakaoOauthService.getAccessToken(code);
		HashMap<String, Object> userInfo = kakaoOauthService.getUserInfoFromOauth(accessTocken);
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

		String token = jwtService.create("uId", user.getUid(), "access-token");
		logger.debug("#토큰정보: " + token);
		resultMap.put("access-token", token);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
	}

	/**
	 * 네이버 아이디 로그인
	 * 
	 * @param - (네이버 서버에서 응답)
	 * @return UserDto, BlogDto, UserInfo, message
	 */
	@ApiOperation(value = "네이버 아이디로 로그인 하기", notes = " 네이버 아이디로 로그인시, 해당 회원 정보를 리턴한다.<br> 최초 로그인인 경우 회원가입이 진행된 후 가입정보를 리턴한다.<br> "
			+ "@param: UserDto, BlogDto, UserInfoDto, message")
	@GetMapping("/login/naver")
	public ResponseEntity<Map<String, Object>> loginByNaver(@RequestParam("code") String code) {
		String access_Token = naverOauthService.getAccessToken(code);
		HashMap<String, Object> userInfo = naverOauthService.getUserInfoFromOauth(access_Token);
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

		String token = jwtService.create("uId", user.getUid(), "access-token");
		logger.debug("#토큰정보: " + token);
		resultMap.put("access-token", token);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
	}

	@GetMapping("/test/user")
	public ResponseEntity<Map<String, Object>> accessInfo(String uId, HttpServletRequest request) {
		logger.info("#회원정보 페이지 접속");
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("#사용가능한 토큰");
			logger.info("#uId : {}", uId);
			logger.info("#jwtGetUserInfo : {}", jwtService.getUserId());

			UserDto user = null;
			try {
				user = userService.findById(uId);
				if (user != null) {
					resultMap.put("message", SUCCESS);
					resultMap.put("userInfo", user);
				} else {
					logger.warn("#userInfo 검색실패");
					resultMap.put("message", FAIL);
				}
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("message", e.getMessage());
			}

			status = HttpStatus.ACCEPTED;
		} else {
			logger.warn("#사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
	}

}
