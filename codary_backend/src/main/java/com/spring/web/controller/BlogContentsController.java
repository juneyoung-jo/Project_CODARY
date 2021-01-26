package com.spring.web.controller;

import java.util.List;

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
import com.spring.web.service.BlogContentsService;

@RestController
@RequestMapping("/blog")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class BlogContentsController {
	@Autowired
	private BlogContentsService contentsService;

	@GetMapping("{blogId}/{blogContentsId}")
	public ResponseEntity<BlogContentsDto> get(@PathVariable int blogId, @PathVariable int blogContentsId) throws Exception{
		return new ResponseEntity<BlogContentsDto>(contentsService.getContent(blogId, blogContentsId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<List<BlogContentsDto>> write(@RequestBody BlogContentsDto content) throws Exception{
		contentsService.writeBlogContent(content);
		return new ResponseEntity<List<BlogContentsDto>>(contentsService.listBlogContents(content.getBlogId()), HttpStatus.OK);
	}
	
//	@GetMapping("{blogId}")
//	public ResponseEntity<List<BlogContentsDto>> list(@PathVariable int blogId) throws Exception{
//		return new ResponseEntity<List<BlogContentsDto>>(contentsService.listBlogContents(blogId), HttpStatus.OK);
//	}
	
	@PutMapping
	public ResponseEntity<BlogContentsDto> modify(@RequestBody BlogContentsDto content) throws Exception{
		contentsService.modifyBlogContent(content);
		return new ResponseEntity<BlogContentsDto>(contentsService.getContent(content.getBlogId(), content.getBlogContentsId()), HttpStatus.OK);
	}
	
	@DeleteMapping("{blogId}/{blogContentsId}")
	public ResponseEntity<List<BlogContentsDto>> delete(@PathVariable int blogId, @PathVariable int blogContentsId) throws Exception{
		contentsService.deleteBlogContent(blogId, blogContentsId);
		return new ResponseEntity<List<BlogContentsDto>>(contentsService.listBlogContents(blogId), HttpStatus.OK);
	}
	
}
