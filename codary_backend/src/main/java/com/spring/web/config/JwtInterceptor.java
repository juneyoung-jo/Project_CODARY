package com.spring.web.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.web.error.UnauthorizedException;
import com.spring.web.service.JwtService;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	
	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
	
	private static final String HEADER_AUTH = "access_token";

	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("# 헤더 내 access_token 값 체크");
		// OPTION 요청은 통과
		if(request.getMethod().equals("OPTIONS")) {
			return true;
		}else {	
		// GET, POST, UPDATE, DELETE 요청은 권한 체크
			final String token = request.getHeader(HEADER_AUTH);
			if(token != null && jwtService.isUsable(token)){
				logger.info("토큰 사용 가능 : {}", token);
				return true;
			}else{
				System.out.println("intercepter 컷");
				logger.info("토큰 사용 불가능 : {}", token);
				throw new UnauthorizedException();
			}
		}
	}
}
