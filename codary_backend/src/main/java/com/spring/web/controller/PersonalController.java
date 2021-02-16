package com.spring.web.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.UserInfoDto;
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
	
	public static final Logger logger = LoggerFactory.getLogger(PersonalController.class);
	
	@Autowired
	private PersonalService personalService;
	

	/**
	 * 블로그의 모든 글 가져오기
	 * 
	 * @param blogId
	 * @return List<BlogPostDto>
	 */
	@ApiOperation(value ="블로그의 모든 글 가져오기", notes = "@param blogId </br> @return BlogPostDto", response=List.class)
	@GetMapping("/{blogid}")
	public ResponseEntity<List<BlogPostDto>> personalList(@PathVariable String blogid) {
		try {
			List<BlogPostDto> list = personalService.personalContents(blogid);
			Collections.sort(list, new Comparator<BlogPostDto>() {
				@Override
				public int compare(BlogPostDto o1, BlogPostDto o2) {
					return o2.getBlogDatetime().compareTo(o1.getBlogDatetime());
				}
			});
			return new ResponseEntity<List<BlogPostDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	/*내 메모 불러오기 (최신순)*/
	@ApiOperation(value="내 메모 불러오기 (최신순)", notes = "내가쓴 메모 목록을 최신순으로 반환한다.", response=List.class)
	@GetMapping("/memo/{memoid}") 
	public ResponseEntity<List<MemoContentsDto>> showMyMemo(@PathVariable String memoid, HttpServletRequest request) {
		
		HttpStatus status=HttpStatus.ACCEPTED;
		List<MemoContentsDto> memocontentsDto=null;
		
	//	if(jwtService.isUsable(request.getHeader("access-token"))) { //로그인 되었다면
			try {
				memocontentsDto=personalService.showMemo(memoid);
				Collections.sort(memocontentsDto, new Comparator<MemoContentsDto>() {
					@Override
					public int compare(MemoContentsDto o1, MemoContentsDto o2) {
						return o2.getMemoTime().compareTo(o1.getMemoTime());
					}
				});
				status=HttpStatus.ACCEPTED;
			}catch(Exception e) {
				e.printStackTrace();
				status=HttpStatus.INTERNAL_SERVER_ERROR;
			}
	//	}else { 
	//		status=HttpStatus.ACCEPTED;
	//	}
	
		return new ResponseEntity<List<MemoContentsDto>>(memocontentsDto, status);
	}
	
	
	/*좋아요한 블로거 목록보기*/
	@ApiOperation(value="좋아요한 블로거 목록 보기", notes="내가 좋아요한 블로거들의 목록을 반환한다.", response=List.class)
	@GetMapping("/bloger/{blogid}/{uid}")
	public ResponseEntity<List<Map<String, Object>>> showMyBloger(@PathVariable String blogid, @PathVariable String uid, HttpServletRequest request){

		HttpStatus status=HttpStatus.ACCEPTED;
		List<Map<String, Object>> m=null;
		
	//		if(jwtService.isUsable(request.getHeader("access-token"))) { //로그인 되었다면
				try {
					m=personalService.showLikeBloger(uid);
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
	//		}else { 
	//			status=HttpStatus.ACCEPTED;
	//		}
		
			return new ResponseEntity<List<Map<String, Object>>>(m, status);
	}
	

	/**
	 * 좋아요 누른 블로그 글 가져오기
	 * 
	 * @param blogId
	 * @return List<BlogPostDto>
	 */
	@ApiOperation(value="좋아요 누른 블로그 글 가져오기", notes="@param blogId </br> @return List<BlogPostDto>", response=List.class)
	@GetMapping("/blog/{blogid}/{uid}")
	public ResponseEntity<List<BlogPostDto>> showMyBlogContents(@PathVariable String blogid, @PathVariable String uid, HttpServletRequest request){
		try {
			List<BlogPostDto> list = personalService.showLikeBlogContents(uid);
			Collections.sort(list, new Comparator<BlogPostDto>() {
				@Override
				public int compare(BlogPostDto o1, BlogPostDto o2) {
					return o2.getBlogDatetime().compareTo(o1.getBlogDatetime());
				}
			});
			return new ResponseEntity<List<BlogPostDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	/*잔디*/
	@ApiOperation(value="잔디", notes="잔디 그래프에 필요한 값(블로그 글을 등록한 횟수)를 반환한다.", response=List.class)
	@GetMapping("/jandi/{blogid}")
	public ResponseEntity<List<Map<String,Integer>>> showjandi(@PathVariable String blogid) throws ParseException{
		
		HttpStatus status=HttpStatus.ACCEPTED;
		List<Map<String, Integer>> result=null;
		
		//	if(jwtService.isUsable(request.getHeader("access-token"))) { //로그인 되었다면
				try {
					result=personalService.jandi(blogid);
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
		//	}else { 
				
		//	}
		
		return new ResponseEntity<List<Map<String,Integer>>>(result, status);
	}
	
	/*유저통계*/
	@ApiOperation(value="유저통계", notes="유저통계 그래프에 필요한 값(블로그에 방문한 횟수)를 반환한다.", response=List.class)
	@GetMapping("/usergraph/{blogid}")
	public ResponseEntity<List<Map<String,Integer>>> usergraph(@PathVariable String blogid) throws ParseException{
		
		HttpStatus status=HttpStatus.ACCEPTED;
		List<Map<String, Integer>> result=null;
		
		//	if(jwtService.isUsable(request.getHeader("access-token"))) { //로그인 되었다면
				try {
					result=personalService.usergraph(blogid);
					status=HttpStatus.ACCEPTED;
				}catch(Exception e) {
					e.printStackTrace();
					status=HttpStatus.INTERNAL_SERVER_ERROR;
				}
		//	}else { 
				
		//	}
		
		return new ResponseEntity<List<Map<String,Integer>>>(result, status);
	}
	
	/*블로거 좋아요 (팔로우)*/
	@ApiOperation(value="블로거 좋아요 누르기 (팔로우)", notes ="@param BlogerLikeDto </br> @return ")
	@PostMapping("blogerLike")
	public ResponseEntity<Map<String, String>> blogerLike(@RequestBody BlogerLikeDto bld) throws Exception{
		
		HttpStatus status=HttpStatus.ACCEPTED;
		Map<String, String> result=new HashMap<>();
		
		try {
			personalService.blogerLike(bld);
			result.put("msg", "success");
			status=HttpStatus.OK;
		}catch(Exception e) {
			result.put("msg", "fail");
			e.printStackTrace();
			status=HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Map<String, String>>(result,status);
	}
	
	/*블로거 좋아요 취소(언팔로우)*/
	@ApiOperation(value="블로거 좋아요 누르기 취소 (언팔로우)", notes ="@param BlogerLikeDto </br> @return ")
	@PostMapping("blogerUnlike")
	public ResponseEntity<Map<String, String>> blogerUnlike(@RequestBody BlogerLikeDto bld) throws Exception{
		
		HttpStatus status=HttpStatus.ACCEPTED;
		Map<String, String> result=new HashMap<>();
		
		try {
			personalService.blogerUnlike(bld);
			result.put("msg", "success");
			status=HttpStatus.OK;
		}catch(Exception e) {
			result.put("msg", "fail");
			e.printStackTrace();
			status=HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Map<String, String>>(result,status);
	}
	
	/*블로거 좋아요 누른 여부*/
	@ApiOperation(value="블로거 좋아요 누른 여부", notes ="@param BlogerLikeDto </br> @return ")
	@PostMapping("checkBlogerlike")
	public ResponseEntity<Map<String, Object>> readBlogerlike(@RequestBody BlogerLikeDto bld) throws Exception{
		
		HttpStatus status=HttpStatus.ACCEPTED;
		Map<String, Object> result = new HashMap<String,Object>();
		try {
			boolean data = personalService.readBlogerLike(bld);
			result.put("msg", "success");
			result.put("data", data);
			status=HttpStatus.OK;
		}catch(Exception e) {
			result.put("msg", "fail");
			e.printStackTrace();
			status=HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Map<String, Object>>(result,status);
	}
	
	/*타인의 개인블로그에 필요한 유저정보를 받는다 */
	@ApiOperation(value ="유저정보", notes = "타인의 개인블로그에 필요한 유저 정보를 받는다")
	@GetMapping("/userinfo/{blogid}")
	public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String blogid) {
		UserInfoDto udo=null;
		HttpStatus status=HttpStatus.ACCEPTED;
		try {
			udo=personalService.findUser(blogid);
			status=HttpStatus.OK;
		}catch(Exception e) {
			e.printStackTrace();
			status=HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<UserInfoDto>(udo,status);
	}
	
	@ApiOperation(value ="블로그 정보 다 가져오기.", notes = "블로그 정보 가져오기")
	@GetMapping("/blogInfo/{blogid}")
	public ResponseEntity<BlogDto> getBlogInfo(@PathVariable String blogid) {
		logger.info("=======내 블로그 정보 읽어오기=======");
		BlogDto blog=null;
		HttpStatus status=HttpStatus.ACCEPTED;
		try {
			blog=personalService.findBlog(blogid);
			status=HttpStatus.OK;
		}catch(Exception e) {
			e.printStackTrace();
			status=HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<BlogDto>(blog,status);
	}
	
	@ApiOperation(value ="블로그 커버 수정", notes = "블로그 커버 수정")
	@PutMapping("/modifyBlogCover")
	public ResponseEntity<Map<String,Object>> modifyBlogCover(@RequestBody Map<String,Object> cover) {
		logger.info("=======내 블로그 정보 읽어오기=======");
		System.out.println(cover.toString());
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		
		try {
			personalService.modifyBlogCover(cover);
			map.put("msg", "success");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}catch(Exception e) {
			map.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return resEntity;
	}
}
