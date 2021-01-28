package com.spring.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.CommentDto;
import com.spring.web.service.BlogContentsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("BlogContentsController V1")
@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class BlogContentsController {
	@Autowired
	private BlogContentsService contentsService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);


	/**
	 * 해당 블로그의 특정 블로그 글 가져오기
	 * 
	 * @param blogId, blogContentsId
	 * @return BlogContentsDto
	 */
	@ApiOperation(value = "해당 블로그의 특정 블로그 글 가져오기", notes ="@param blogId, blogContentsId  </br> @return BlogContentsDto")
	@GetMapping("{blogId}/{blogContentsId}")
	public ResponseEntity<BlogContentsDto> get(@PathVariable String blogId, @PathVariable int blogContentsId) throws Exception{
		try {
			return new ResponseEntity<BlogContentsDto>(contentsService.getContent(blogContentsId), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 블로그 글 작성
	 * 
	 * @param BlogContentsDto
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "블로그 글 작성", notes ="@param BlogContentsDto  </br> @return List<BlogContentsDto>")
	@PostMapping
	public ResponseEntity<List<BlogContentsDto>> write(@RequestBody BlogContentsDto content) throws Exception{
		try {
			contentsService.writeBlogContent(content);
			return new ResponseEntity<List<BlogContentsDto>>(contentsService.listBlogContents(content.getBlogId()), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
//	@GetMapping("{blogId}")
//	public ResponseEntity<List<BlogContentsDto>> list(@PathVariable int blogId) throws Exception{
//		return new ResponseEntity<List<BlogContentsDto>>(contentsService.listBlogContents(blogId), HttpStatus.OK);
//	}
	
	/**
	 * 블로그 글 수정
	 * 
	 * @param BlogContentsDto
	 * @return BlogContentsDto
	 */
	@ApiOperation(value = "블로그 글 수정", notes ="@param BlogContentsDto  </br> @return BlogContentsDto")
	@PutMapping
	public ResponseEntity<BlogContentsDto> modify(@RequestBody BlogContentsDto content) throws Exception{
		try {
			contentsService.modifyBlogContent(content);
			return new ResponseEntity<BlogContentsDto>(contentsService.getContent(content.getBlogContentsId()), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 블로그 글 삭제
	 * 
	 * @param blogId, blogContentsId
	 * @return List<BlogContentsDto>
	 */
	@ApiOperation(value = "블로그 글 삭제", notes ="@param blogId, blogContentsId  </br> @return List<BlogContentsDto>")
	@DeleteMapping("{blogId}/{blogContentsId}")
	public ResponseEntity<List<BlogContentsDto>> delete(@PathVariable String blogId, @PathVariable int blogContentsId) throws Exception{
		try {
			contentsService.deleteBlogContent(blogContentsId);
			return new ResponseEntity<List<BlogContentsDto>>(contentsService.listBlogContents(blogId), HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 내 블로그 글 클릭 시 Log남기고 글 가져오기
	 * 
	 * @param blogId, blogContentsId
	 * @return BlogContentsDto
	 */
	@ApiOperation(value = "내 블로그 글 클릭 시 Log남기고 글 가져오기", notes ="@param uid, blogId, blogContentsId  </br> @return BlogContentsDto")
	@GetMapping("log/{uid}/{blogId}/{blogContentsId}")
	public ResponseEntity<Map<String,Object>> writeLog(@PathVariable String uid, @PathVariable String blogId, @PathVariable int blogContentsId) throws Exception{
		logger.info("=======내 블로그 글 클릭 시 Log남기고 글 가져오기=======");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			BlogContentsDto data = contentsService.writeLog(uid,blogId, blogContentsId);
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
