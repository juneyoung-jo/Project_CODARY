package com.spring.web.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.spring.web.config.auth.PrincipalDetails;
import com.spring.web.dto.UserDto;
import com.spring.web.service.UserService;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	// 구글로부터 받은 userRequest 데이터에 대한 후처리 함수.
	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		// 어떤 oAuth로 로그인했는지 확인가능
		System.out.println("getClientRegistration: " + userRequest.getClientRegistration());
		System.out.println("getAccessToken: " + userRequest.getAccessToken());

		OAuth2User oAuth2User = super.loadUser(userRequest);
		// 구글로그인 버튼 클릭 -> 로그인창 -> 로그인 완료 -> code를 리턴(OAuth-Client라이브러리) -> accessToken 요청
		// userRequest 정보 -> loadUser 함수 -> 회원프로필
		System.out.println("getAttributes: " + oAuth2User.getAttributes());

		String provider = userRequest.getClientRegistration().getClientId(); //google
		String providerId = oAuth2User.getAttribute("sub"); // 구글 primary key(id)
		String username = provider+"_"+providerId; // google_1235464815321
//		String password = bCryptPasswordEncoder.encode("겟인데어");
		String email = oAuth2User.getAttribute("eamil");
		String role = "ROLE_USER";
		
		UserDto userEntity = userService.findById(username);
		
		if(userEntity == null) {
			userEntity = new UserDto();
			System.out.println("구글 로그인이 최초입니다.");
//			userEntity = new UserDto(username, blogId, memoId, aulKey, aulId, aulDatetime);
//			userService.save(userEntity);
		}else {
			System.out.println("당신은 이미 가입된 회원입니다.");
		}
		
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}
}
