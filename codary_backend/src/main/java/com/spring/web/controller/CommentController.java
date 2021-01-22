package com.spring.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.CommentDto;
import com.spring.web.dto.UserInfoDto;
import com.spring.web.service.CommentService;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentSer;

	/**
	 * 
	 * 현재 클릭 한 블로그 글의 댓글 전체 읽어오기
	 * 
	 * @param blog_id, blog_contents_id
	 * @return List<CommentDto>
	 */
	@GetMapping("commentAll/{blogId}/{blogContentsId}")
	public ResponseEntity<Map<String, Object>> listCommnet(@PathVariable int blogId, @PathVariable int blogContentsId) {
		System.out.println("댓글 리스트 읽어오기");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<CommentDto> data = commentSer.listComment(blogId, blogContentsId);
			map.put("msg", "success");
			map.put("data", data);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			e.printStackTrace();
		}
		return resEntity;
	}
	
	/**
	 * 
	 * 해당 댓글의 유저정보를 읽어오기 -profile, nickName 정보 등을 얻기위해
	 * */
	@PostMapping("getUserInfo")
	public ResponseEntity<Map<String,Object>> getUserInfo(@RequestBody UserInfoDto info){
		System.out.println("댓글의 유저정보 읽어오기");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			UserInfoDto data = commentSer.getUserInfo(info);
			map.put("msg", "success");
			map.put("data", data);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put("msg", "fail");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			e.printStackTrace();
		}
		return resEntity;
	}
	

}
