package com.spring.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
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
			contents = dao.listBlogContents("2");
			assertEquals(contents.size(), 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertTest() {
		BlogContentsDto blog = new BlogContentsDto();
		blog.setBlogId("2");
		blog.setBlogContentsTitle("Blog Content Test");
		blog.setBlogContents("2 insert test 2");
		blog.setBlogContentsCover("blog contents cover");
		
		try {
			dao.writeBlogContent(blog);
			List<BlogContentsDto> contents;
			contents = dao.listBlogContents("2");
			assertEquals(contents.size(), 2);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void modifyTest() {
		try {
			BlogContentsDto blog = dao.getContent(6);
			blog.setBlogContents("modify test");
			
			dao.modifyBlogContent(blog);
			
			assertEquals("modify test", dao.getContent(6).getBlogContents());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteTest() {
		try {
			dao.deleteBlogContent(6);
			
			List<BlogContentsDto> contents;
			contents = dao.listBlogContents("2");
			assertEquals(contents.size(), 1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
