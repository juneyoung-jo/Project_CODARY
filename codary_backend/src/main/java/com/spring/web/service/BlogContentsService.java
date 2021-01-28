package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.BlogContentsDto;

public interface BlogContentsService {
	
	BlogContentsDto getContent(int blogContentsId) throws Exception;
	void writeBlogContent(BlogContentsDto blogContent) throws Exception;
	List<BlogContentsDto> listBlogContents(String blogId) throws Exception;
	void modifyBlogContent(BlogContentsDto blogContent) throws Exception;
	void deleteBlogContent(int blogContentsId) throws Exception;
	List<BlogContentsDto> recommendBlogContents() throws Exception;
	BlogContentsDto writeLog(String uid, String blogId, int blogContentsId)throws Exception;

}
