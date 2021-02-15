package com.spring.web.service;

import java.util.List;
import java.util.Map;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogHashtagDto;
import com.spring.web.dto.HashtagDto;
import com.spring.web.dto.UserInfoDto;

public interface BlogContentsService {

	BlogContentsDto getContent(int blogContentsId) throws Exception;

	int writeBlogContent(BlogContentsDto blogContent) throws Exception;
	
	void writeHash(HashtagDto hash) throws Exception;
	
	void writeBlogHash(BlogHashtagDto blogHash) throws Exception;

	List<BlogContentsDto> listBlogContents(String blogId) throws Exception;

	int modifyBlogContent(BlogContentsDto blogContent);
	
	void deleteOldHash(BlogContentsDto blogContent) throws Exception;

	int deleteBlogContent(int blogContentsId);
	
	List<Map<String, Object>> recommendBlogContents() throws Exception;
	
	BlogContentsDto writeLog(String uid, String blogId, int blogContentsId)throws Exception;
	
	void increaseContentsView(int blogContentsId) throws Exception;

	BlogContentsLikeDto readBlogContentsLike(BlogContentsLikeDto like) throws Exception;

	void contentLike(BlogContentsLikeDto like) throws Exception;

	void contentUnlike(BlogContentsLikeDto like) throws Exception;

	UserInfoDto userInfo(String blogId) throws Exception;

	List<HashtagDto> selectHash(String keyword) throws Exception;

	HashtagDto findTagByValue(String value) throws Exception;
}
