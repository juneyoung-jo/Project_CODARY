package com.spring.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.web.dao.BlogContentsDao;
import com.spring.web.dto.BlogContentsDto;

@SpringBootTest
public class blogContentsTest {

	@Autowired
	BlogContentsDao dao;
	
	@Test
	public void listTest() {
		List<BlogContentsDto> contents;
		try {
			contents = dao.listBlogContents(2);
			assertEquals(contents.size(), 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertTest() {
		BlogContentsDto blog = new BlogContentsDto();
		blog.setBlogId(2);
		blog.setBlogContentsTitle("Blog Content Test");
		blog.setBlogContents("insert test");
		blog.setBlogContentsCover("blog contents cover");
		
		try {
			dao.writeBlogContent(blog);
			List<BlogContentsDto> contents;
			contents = dao.listBlogContents(2);
			assertEquals(contents.size(), 2);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void modifyTest() {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("blogId", 2);
			map.put("blogContentsId", 5);
			
			BlogContentsDto blog = dao.getContent(map);
			blog.setBlogContents("modify test");
			
			dao.modifyBlogContent(blog);
			
			assertEquals("modify test", dao.getContent(map).getBlogContents());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest() {
		try {
			Map<String, Integer> map = new HashMap<>();
			map.put("blogId", 2);
			map.put("blogContentsId", 5);
			
			dao.deleteBlogContent(map);
			
			List<BlogContentsDto> contents;
			contents = dao.listBlogContents(2);
			assertEquals(contents.size(), 1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
