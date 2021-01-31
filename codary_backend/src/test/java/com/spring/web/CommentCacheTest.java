package com.spring.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.web.dto.CommentCountDto;
import com.spring.web.dto.UserDto;
import com.spring.web.service.CommentService;

@SpringBootTest
public class CommentCacheTest {

	@Autowired
	CommentService cSer;
	private static final Logger logger = LoggerFactory.getLogger(CommentCacheTest.class);
	
	private long startTime;
	private long endTime;
	
	@BeforeEach
	public void onBefore() {
		startTime = System.currentTimeMillis();
	}
	
	@AfterEach
	public void onAfter() {
		endTime = System.currentTimeMillis();
		logger.info("소요시간: {}ms", endTime - startTime);
	}
	
	@Test
	public void cache() {
		UserDto user = new UserDto();
		user.setUid("1");
		user.setBlogId("1");

		try {
			List<CommentCountDto> list = cSer.commentCheck(user);
			assertEquals(list.size(), 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void cache2() {
		UserDto user = new UserDto();
		user.setUid("1");
		user.setBlogId("1");

		try {
			List<CommentCountDto> list = cSer.commentCheck(user);
			assertEquals(list.size(), 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void cache3() {
		UserDto user = new UserDto();
		user.setUid("1");
		user.setBlogId("1");

		try {
			List<CommentCountDto> list = cSer.commentCheck(user);
			assertEquals(list.size(), 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		@Test
		public void cache4() {
			UserDto user = new UserDto();
			user.setUid("1");
			user.setBlogId("1");

			try {
				List<CommentCountDto> list = cSer.commentCheck(user);
				assertEquals(list.size(), 2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
