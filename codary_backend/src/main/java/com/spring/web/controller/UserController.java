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

import com.spring.web.dto.LoginCallBackDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;
import com.spring.web.service.JwtServiceImpl;
import com.spring.web.service.UserService;
import com.spring.web.service.oauth.GoogleOauthService;
import com.spring.web.service.oauth.KakaoOauthService;
import com.spring.web.service.oauth.NaverOauthService;
import com.spring.web.service.oauth.SocialOauth;

import io.swagger.annotations.ApiOperation;

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

	
	/**
	 * 소셜로그인 access_token을 전달 받아 정보 전달 
	 * 
	 * @param -
	 * @return List<BlogPostDto>
	 */
	@ApiOperation(value = "소셜사이트로 부터 읽어온 access_token값", notes ="@param : access_token  </br> @return : uid, userInfoDto, blodId, memoId")
	@PostMapping("/login/{socialLoginType}")
	public ResponseEntity<Map<String, Object>> access(@PathVariable("socialLoginType") String socialLoginType,
			@RequestBody String accessTocken) {
		System.out.println("#" + socialLoginType + "로그인 요청됨!!");
		System.out.println("# accessTocken: " + accessTocken);
		SocialOauth socialOauth = getSocialOauth(socialLoginType);

		HashMap<String, Object> userInfo = socialOauth.getUserInfoFromOauth(accessTocken);
		logger.info("#Get userInfo: {}", userInfo);

		UserDto user = null;
		Map<String, Object> resultMap = new HashMap<>();
		LoginCallBackDto loginCallBackDto = new LoginCallBackDto();

		try {
			user = userService.findByProvider(userInfo);
			resultMap.put("message", SUCCESS);
			if (user == null) {
				logger.info("#최초 로그인입니다.");
				loginCallBackDto = userService.save(userInfo);
			} else {
				logger.info("#기존회원입니다.");
				UserInfoDto info = userService.findUserInfoById(user.getUid());
				loginCallBackDto.setUid(user.getUid());
				loginCallBackDto.setBlogId(user.getBlogId());
				loginCallBackDto.setMemoId(user.getMemoId());
				loginCallBackDto.setNickname(info.getNickname());
				loginCallBackDto.setProfile(info.getProfile());
				loginCallBackDto.setProvider(user.getProvider());
				
			}
			resultMap.put("message", SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
		}

		System.out.println(loginCallBackDto.toString());
		String token = jwtService.create("uid", loginCallBackDto.getUid(), "access_token");
		logger.debug("#토큰정보: " + token);
		resultMap.put("access_token", token);
		resultMap.put("user", loginCallBackDto);

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}


	@ApiOperation(value = "닉네임 변경", notes ="@param : uid, 변경될 nickname  </br> @return : uid, userInfoDto, blodId, memoId")
	@PostMapping("/updateNickname")
	public ResponseEntity<Map<String, Object>> updateNickname(@RequestParam("uid") String uid,
			@RequestParam("nickname") String nickname) {

		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("nickname", nickname);
		try {
			userService.updateNickname(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		UserDto user;
		try {
			user = userService.findById(uid);
			resultMap.put("userId", user.getUid());
			resultMap.put("userInfo", userService.findUserInfoById(uid));
			resultMap.put("blogId", user.getBlogId());
			resultMap.put("memoId", user.getMemoId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 정보 삭제", notes ="@param : 삭제할 uid  </br> @return : ")
	@PostMapping("/deleteUser")
	public ResponseEntity<Map<String, Object>> updateNickname(@RequestParam("uid") String uid) {

		try {
			userService.delete(uid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("message", "success");

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	@GetMapping("/jwt/test")
	public String jwtTets() {
		System.out.println("여기 들어왔습니다.");
		return "success";
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
