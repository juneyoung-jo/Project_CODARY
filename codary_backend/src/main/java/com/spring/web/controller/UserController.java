package com.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.UserDto;
import com.spring.web.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public UserDto main() {
		
		UserDto user = userService.login();
		System.out.println(user.toString());
		return user;
	}
	
	
}
