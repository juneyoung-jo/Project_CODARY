package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.BlogContentsDto;

public interface BlogContentsService {
	
	BlogContentsDto getContent(int blogId, int blogContentsId) throws Exception;
	void writeBlogContent(BlogContentsDto blogContent) throws Exception;
	List<BlogContentsDto> listBlogContents(int blogId) throws Exception;
	void modifyBlogContent(BlogContentsDto blogContent) throws Exception;
	void deleteBlogContent(int blogId, int blogContentsId) throws Exception;
	
}
