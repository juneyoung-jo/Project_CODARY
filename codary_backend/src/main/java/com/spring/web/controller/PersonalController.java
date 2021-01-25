package com.spring.web.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.UserDto;
import com.spring.web.service.PersonalService;

@RestController
public class PersonalController {
	
	/*@Autowired
	private JwtServiceImpl jwtService;
	*/
	
	@Autowired
	private PersonalService personalService;
	
	/*블로거가 쓴 글*/
	@GetMapping("/blog/{blogid}")
	public ResponseEntity<List<BlogContentsDto>> personalList(@PathVariable String blogid, HttpSession session) {
		
		HttpStatus status=HttpStatus.ACCEPTED;
		List<BlogContentsDto> blogcontentsList=null;
		
		blogcontentsList= personalService.personalContents(blogid);
		
		return new ResponseEntity<List<BlogContentsDto>>(blogcontentsList, HttpStatus.OK);
	}
	
}
