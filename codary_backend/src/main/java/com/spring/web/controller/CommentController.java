package com.spring.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

import com.spring.web.dto.CommentCountDto;
import com.spring.web.dto.CommentDto;
import com.spring.web.dto.CommentToLikeDto;
import com.spring.web.dto.UserDto;
import com.spring.web.dto.UserInfoDto;
import com.spring.web.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("CommentController V1")
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentSer;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 
	 * 현재 클릭 한 블로그 글의 댓글 전체 읽어오기
	 * 
	 * @param blog_id, blog_contents_id
	 * @return List<CommentDto>
	 */
	@ApiOperation(value = "현재 클릭 한 블로그 글의 댓글 전체 읽어오기", notes ="@param : blogId, blogContentsId  </br> @return : List<CommentDto>")
	@GetMapping("commentAll/{blogId}/{blogContentsId}")
	public ResponseEntity<Map<String, Object>> listCommnet(@PathVariable String blogId, @PathVariable int blogContentsId) {
		logger.info("=======댓글 리스트 읽어오기=======");
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
	@ApiOperation(value = "해당 댓글의 유저정보를 읽어오기 -profile, nickName 정보 등을 얻기", notes ="@param : uid  </br> @return : userInfo")
	@PostMapping("getUserInfo")
	public ResponseEntity<Map<String, Object>> getUserInfo(@RequestBody UserInfoDto info) {
		logger.info("=======댓글의 유저정보 읽어오기=======");
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
	@ApiOperation(value = "댓글 쓰기기능", notes ="@param : blogContentsId, blogId, commentContent,uid  </br>")
	@PostMapping("writeComment")
	public ResponseEntity<Map<String, Object>> writeComment(@RequestBody CommentDto comment) {
		logger.info("=======댓글쓰기=======");
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
	@ApiOperation(value = "댓글 수정기능", notes ="@param : commentNum,commentContent  </br>")
	@PutMapping("modifyComment")
	public ResponseEntity<Map<String, Object>> modifyComment(@RequestBody CommentDto comment) {
		logger.info("=======댓글 수정=======");
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
	@ApiOperation(value = "댓글 삭제기능", notes ="@param : commentNum</br>")
	@DeleteMapping("deleteComment/{commentNum}")
	public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable int commentNum) {
		logger.info("=======댓글 삭제=======");
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
	@ApiOperation(value = "댓글 좋아요 확인기능", notes ="@param : uid, commentNum</br> @return : true -> 해당 댓글에 내가 좋아요를 누름. false -> 해당 댓글에 내가 좋아요를 안 누름.")
	@PostMapping("getCommentLike")
	public ResponseEntity<Map<String, Object>> getCommentLike(@RequestBody CommentToLikeDto ctl) {
		logger.info("=======댓글 좋아요 체크=======");
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
	
	/**
	 * 
	 * 댓글 좋아요 
	 * 
	 * @param uid,commentNum
	 * 
	 */
	@ApiOperation(value = "댓글 좋아요 누르기 ", notes ="@param : uid, commentNum</br>")
	@PostMapping("commentLike")
	public ResponseEntity<Map<String, Object>> commentLike(@RequestBody CommentToLikeDto ctl) {
		logger.info("=======해당 댓글의 좋아요 누르기=======");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			commentSer.commentLike(ctl);
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
	 * 댓글 좋아요  취소
	 * 
	 * @param uid,commentNum
	 * 
	 */
	@ApiOperation(value = "댓글 좋아요 취소하기 ", notes ="@param : uid, commentNum</br>")
	@PostMapping("commentLikeCancle")
	public ResponseEntity<Map<String, Object>> commentLikeCancle(@RequestBody CommentToLikeDto ctl) {
		logger.info("=======해당 댓글의 좋아요 취소하기=======");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			commentSer.commentLikeCancle(ctl);
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
	 * 확인하지 않은 댓글 카운팅 
	 * 
	 * @param uid,blogId
	 * @return null 이면 확인 안한 댓글이 없음
	 *
	 */
	@ApiOperation(value = "확인하지 않은 댓글 카운트", notes ="@param : uid,blogId </br> @return : null -> 확인 안한 댓글이 없음 / 있으면 리스트로 던짐")
	@PostMapping("commentCheck")
	public ResponseEntity<Map<String, Object>> commentCheck(@RequestBody UserDto user) {
		logger.info("=======확인하지 않은 댓글 카운트 =======");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List<CommentCountDto> data = commentSer.commentCheck(user);
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
