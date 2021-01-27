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
	public ResponseEntity<List<BlogContentsDto>> personalList(@PathVariable String blogid, HttpSession session) {
		
		List<BlogContentsDto> blogcontentsList=null;
		blogcontentsList= personalService.personalContents(blogid);
		
		return new ResponseEntity<List<BlogContentsDto>>(blogcontentsList, HttpStatus.OK);
	}
	
	/*내 메모 불러오기*/
	@GetMapping("/blog/memo/{memoid}") 
	public ResponseEntity<List<MemoContentsDto>> showMyMemo(@PathVariable String memoid, HttpServletRequest request) {
		
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
	public ResponseEntity<List<BlogDto>> showMyBloger(@PathVariable String blogid, @PathVariable String uid){

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
	public ResponseEntity<List<BlogContentsDto>> showMyBlogContents(@PathVariable String blogid, @PathVariable String uid){

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
	public ResponseEntity<List<Map<String, Integer>>> showjandi(@PathVariable String blogid){
		List<Map<String, Integer>> list=new ArrayList<>();
		/*HttpStatus status=HttpStatus.ACCEPTED;
		List<JandiDto> jandiDto=null;	
		Map<String, Integer> resultMap=new HashMap<>();
		List<Integer> l=new ArrayList<>();
		//int arr[]=new int[365];
		
		
		//	if(jwtService.isUsable(request.getHeader("access-token")) { 
				try {
					jandiDto=personalService.jandi(blogid);
					
					//jandiDto에서 가져온 마지막날짜
					int start=Integer.parseInt(jandiDto.get(jandiDto.size()-1).getBlogDatetime().replace("-", ""));
					//System.out.println(start);
					
					ArrayList<Integer> day=new ArrayList<>();	//날짜 : ex) 20210127
					ArrayList<Integer> cnt=new ArrayList<>(); 	//횟수 : ex) 3
					
					ArrayList<Integer> result=new ArrayList<>();
					
					/*for(int i=0; i<jandiDto.size(); i++) {
						day.add(Integer.parseInt(jandiDto.get(i).getBlogDatetime().replace("-", "")));
						cnt.add(jandiDto.get(i).getJandiCnt());
					}*/
					/*
					int nn=start-365;
					int size=0;
					for(int i=0; i<365; i++) {
						
							/*if(size<=jandiDto.size()) {
								day.add(Integer.parseInt(jandiDto.get(size).getBlogDatetime().replace("-", "")));
								cnt.add(jandiDto.get(size).getJandiCnt());
								size++;
							}
							else {
								cnt.add(0);
							}*/
					/*	if(size<jandiDto.size()) {
							if(nn==Integer.parseInt(jandiDto.get(size).getBlogDatetime().replace("-", ""))) {
								result.add(jandiDto.get(size).getJandiCnt());
								size++;
							}
							else {
								result.add(0);
							}
						}
							nn++;
					}
					*/
					/*int n=jandiDto.size()-1;
					for(int i=start; i>start-365; i--) {
						if(n>=0) {
							result.add(cnt.get(n));
						}
						else {
							result.add(0);
						}
						n--;
						//resultMap.put(day.get(n).get
					}*/
					//result.get(day.get(i))
					//int starttime=day.get(day.size()-1);
					
					/*int nn=0;
					for(int i=0; i<365; i++) {
						if(nn<=jandiDto.size()-1) 
							if() {
								
							}
						 
						if(){
							//resultMap.put(jandiDto.get(i).getBlogDatetime(),jandiDto.get(i).getJandiCnt());
							System.out.println("if2지롱");
							l.add(jandiDto.get(i).getJandiCnt());
							System.out.println("if2지롱");
						}else {
							l.add(0);
							System.out.println("else지롱");
						}
					}
					
					list.add(resultMap);
					
					for(int i=0; i<result.size(); i++) {
						System.out.println(i+" : "+result.get(i));
					}
					
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
		//	}else { 
			
		//	}
		*/
			return new ResponseEntity<List<Map<String, Integer>>>(list, HttpStatus.OK);
	}
}
