package com.spring.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.JandiDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.MemoDto;
import com.spring.web.dto.UserDto;
import com.spring.web.service.PersonalService;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class PersonalController {
	
	/*@Autowired
	private JwtServiceImpl jwtService;
	*/
	
	@Autowired
	private PersonalService personalService;
	
	/*블로거가 쓴 글*/
	@GetMapping("/blog/{blogid}")
	public ResponseEntity<List<BlogContentsDto>> personalList(@PathVariable int blogid, HttpSession session) {
		
		List<BlogContentsDto> blogcontentsList=null;
		blogcontentsList= personalService.personalContents(blogid);
		
		return new ResponseEntity<List<BlogContentsDto>>(blogcontentsList, HttpStatus.OK);
	}
	
	/*내 메모 불러오기*/
	@GetMapping("/blog/memo/{memoid}") 
	public ResponseEntity<List<MemoContentsDto>> showMyMemo(@PathVariable int memoid, HttpServletRequest request) {
		
		HttpStatus status=HttpStatus.ACCEPTED;
		List<MemoContentsDto> memocontentsDto=null;
		
	//	if(jwtService.isUsable(request.getHeader("access-token")) { 
			try {
				memocontentsDto=personalService.showMemo(memoid);
				System.out.println(memocontentsDto);
				status=HttpStatus.ACCEPTED;
			}catch(Exception e) {
				e.printStackTrace();
				status=HttpStatus.INTERNAL_SERVER_ERROR;
			}
	//	}else { 
		
	//	}
	
		return new ResponseEntity<List<MemoContentsDto>>(memocontentsDto, HttpStatus.OK);
	}
	
	/*좋아요한 블로거 목록보기*/
	@GetMapping("/blog/{blogid}/{uid}")
	public ResponseEntity<List<BlogDto>> showMyBloger(@PathVariable int blogid, @PathVariable String uid){

		HttpStatus status=HttpStatus.ACCEPTED;
		List<BlogDto> blogDto=null;
		
		//	if(jwtService.isUsable(request.getHeader("access-token")) { 
				try {
					blogDto=personalService.showLikeBloger(uid);
					System.out.println(blogDto);
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
		//	}else { 
			
		//	}
		
			return new ResponseEntity<List<BlogDto>>(blogDto, HttpStatus.OK);
	}
	
	/*좋아요한 블로그 글 목록보기*/
	@GetMapping("/{blogid}/{uid}")
	public ResponseEntity<List<BlogContentsDto>> showMyBlogContents(@PathVariable int blogid, @PathVariable String uid){

		HttpStatus status=HttpStatus.ACCEPTED;
		List<BlogContentsDto> blogcontentsDto=null;
		
		//	if(jwtService.isUsable(request.getHeader("access-token")) { 
				try {
					blogcontentsDto=personalService.showLikeBlogContents(uid);
					System.out.println(blogcontentsDto);
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
		//	}else { 
			
		//	}
		
			return new ResponseEntity<List<BlogContentsDto>>(blogcontentsDto, HttpStatus.OK);
	}
	
	/*잔디*/
	@GetMapping("/blog/jandi/{blogid}")
	public ResponseEntity<List<JandiDto>> showjandi(@PathVariable int blogid){
		HttpStatus status=HttpStatus.ACCEPTED;
		List<JandiDto> jandiDto=null;
		
		//	if(jwtService.isUsable(request.getHeader("access-token")) { 
				try {
					jandiDto=personalService.jandi(blogid);
					System.out.println(jandiDto);
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
		//	}else { 
			
		//	}
		
			return new ResponseEntity<List<JandiDto>>(jandiDto, HttpStatus.OK);
	}
}
