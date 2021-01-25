package com.spring.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.dto.CommentDto;
import com.spring.web.dto.CommentToLikeDto;
import com.spring.web.dto.UserInfoDto;
import com.spring.web.service.CommentService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentSer;

	@GetMapping("/")
	public String main() {
		return "hihi";
	}

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
	 * 
	 * @param uid
	 * @return userInfo
	 */
	@PostMapping("getUserInfo")
	public ResponseEntity<Map<String, Object>> getUserInfo(@RequestBody UserInfoDto info) {
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

	/**
	 * 
	 * 댓글 쓰기
	 * 
	 * @param #{blogContentsId},#{blogId},#{commentContent},#{uid}
	 *
	 */
	@PostMapping("writeComment")
	public ResponseEntity<Map<String, Object>> writeComment(@RequestBody CommentDto comment) {
		System.out.println("댓글쓰기");
		System.out.println(comment);
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			commentSer.writeComment(comment);
			map.put("msg", "success");
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
	 * 댓글 수정
	 * 
	 * @param commentDto(commentNum, commentContent)
	 * 
	 */
	@PutMapping("modifyComment")
	public ResponseEntity<Map<String, Object>> modifyComment(@RequestBody CommentDto comment) {
		System.out.println("댓글수정");
		System.out.println(comment);
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			commentSer.modifyComment(comment);
			map.put("msg", "success");
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
	 * 댓글 삭제
	 * 
	 * @param commentNum
	 *
	 */
	@DeleteMapping("deleteComment/{commentNum}")
	public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable int commentNum) {
		System.out.println("댓글삭제");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			commentSer.deleteComment(commentNum);
			map.put("msg", "success");
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
	 * 댓글 좋아요 확인
	 * 
	 * @param uid,commentNum
	 * @return true -> 해당 댓글에 내가 좋아요를 누름. false -> 해당 댓글에 내가 좋아요를 안 누름.
	 */
	@PostMapping("getCommentLike")
	public ResponseEntity<Map<String, Object>> getCommentLike(@RequestBody CommentToLikeDto ctl) {
		System.out.println("댓글의 유저정보 읽어오기");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			boolean data = commentSer.getCommentLike(ctl);
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
