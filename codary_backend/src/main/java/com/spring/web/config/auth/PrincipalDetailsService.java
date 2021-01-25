package com.spring.web.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.web.dto.UserDto;
import com.spring.web.service.UserService;

// http://localhost:8080/login => 여기서 더이상 동작안한다.
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private UserService userService;
	public PrincipalDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("principalDetailsService의 loadUserByUsername");
		UserDto userEntity = userService.findById(username);
		return new PrincipalDetails(userEntity);
	}
	
}
