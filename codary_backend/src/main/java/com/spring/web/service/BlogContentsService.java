package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogPostDto;
import com.spring.web.dto.UserInfoDto;

public interface BlogContentsService {
	
	BlogContentsDto getContent(int blogContentsId) throws Exception;
	void writeBlogContent(BlogContentsDto blogContent) throws Exception;
	List<BlogContentsDto> listBlogContents(String blogId) throws Exception;
	int modifyBlogContent(BlogContentsDto blogContent);
	int deleteBlogContent(int blogContentsId);
	List<BlogPostDto> recommendBlogContents() throws Exception;
	BlogContentsDto writeLog(String uid, String blogId, int blogContentsId)throws Exception;
	void increaseContentsView(int blogContentsId) throws Exception;
	BlogContentsLikeDto readBlogContentsLike(BlogContentsLikeDto like) throws Exception;
	void contentLike(BlogContentsLikeDto like) throws Exception;
	void contentUnlike(BlogContentsLikeDto like) throws Exception;
	UserInfoDto userInfo(String blogId) throws Exception;
	
}
