package com.spring.web.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.JandiDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.MemoDto;
import com.spring.web.dto.UserDto;
import com.spring.web.service.JwtServiceImpl;
import com.spring.web.service.PersonalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("PersonalController V1")
@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/personal")
public class PersonalController {
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private PersonalService personalService;
	
	/*블로거가 쓴 글*/
	@ApiOperation(value ="블로거가 쓴 글", notes = "해당 블로거가 쓴 글 목록을 반환한다.", response=List.class)
	@GetMapping("/{blogid}")
	public ResponseEntity<List<BlogContentsDto>> personalList(@PathVariable String blogid) {
		
		List<BlogContentsDto> blogcontentsList=null;
		blogcontentsList= personalService.personalContents(blogid);
		
		return new ResponseEntity<List<BlogContentsDto>>(blogcontentsList, HttpStatus.OK);
	}
	
	/*내 메모 불러오기*/
	@ApiOperation(value="내 메모 불러오기", notes = "내가쓴 메모 목록을 반환한다.", response=List.class)
	@GetMapping("/memo/{memoid}") 
	public ResponseEntity<List<MemoContentsDto>> showMyMemo(@PathVariable String memoid, HttpServletRequest request) {
		
		HttpStatus status=HttpStatus.ACCEPTED;
		List<MemoContentsDto> memocontentsDto=null;
		
//		if(jwtService.isUsable(request.getHeader("access-token"))) { 
			try {
				memocontentsDto=personalService.showMemo(memoid);
				System.out.println(memocontentsDto);
				status=HttpStatus.ACCEPTED;
			}catch(Exception e) {
				e.printStackTrace();
				status=HttpStatus.INTERNAL_SERVER_ERROR;
			}
//		}else { 
//			status=HttpStatus.ACCEPTED;
//		}
	
		return new ResponseEntity<List<MemoContentsDto>>(memocontentsDto, HttpStatus.OK);
	}
	
	/*좋아요한 블로거 목록보기*/
	@ApiOperation(value="좋아요한 블로거 목록 보기", notes="내가 좋아요한 블로거들의 목록을 반환한다.", response=List.class)
	@GetMapping("/bloger/{blogid}/{uid}")
	public ResponseEntity<List<BlogDto>> showMyBloger(@PathVariable String blogid, @PathVariable String uid, HttpServletRequest request){

		HttpStatus status=HttpStatus.ACCEPTED;
		List<BlogDto> blogDto=null;
		
//			if(jwtService.isUsable(request.getHeader("access-token"))) { 
				try {
					blogDto=personalService.showLikeBloger(uid);
					System.out.println(blogDto);
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
//			}else { 
//				status=HttpStatus.ACCEPTED;
//			}
		
			return new ResponseEntity<List<BlogDto>>(blogDto, HttpStatus.OK);
	}
	
	/*좋아요한 블로그 글 목록보기*/
	@ApiOperation(value="좋아요한 블로그 글 목록보기", notes="내가 좋아효한 블로그 글들의 목록을 반환한다.", response=List.class)
	@GetMapping("/blog/{blogid}/{uid}")
	public ResponseEntity<List<BlogContentsDto>> showMyBlogContents(@PathVariable String blogid, @PathVariable String uid, HttpServletRequest request){

		HttpStatus status=HttpStatus.ACCEPTED;
		List<BlogContentsDto> blogcontentsDto=null;
		
//			if(jwtService.isUsable(request.getHeader("access-token"))) { 
				try {
					blogcontentsDto=personalService.showLikeBlogContents(uid);
					System.out.println(blogcontentsDto);
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
//			}else { 
			
//			}
		
			return new ResponseEntity<List<BlogContentsDto>>(blogcontentsDto, HttpStatus.OK);
	}
	
	/*잔디*/
	@ApiOperation(value="잔디", notes="잔디 그래프에 필요한 값(블로그 글을 등록한 횟수)를 반환한다.", response=List.class)
	@GetMapping("/jandi/{blogid}")
	public ResponseEntity<List<Map<String, Integer>>> showjandi(@PathVariable String blogid){
		List<Map<String, Integer>> list=new ArrayList<>();
		
		return new ResponseEntity<List<Map<String, Integer>>>(list, HttpStatus.OK);
	}
}
