package com.spring.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;

@Mapper
public interface BlogContentsDao {
	
	BlogContentsDto getContent(int blogContentsId) throws SQLException;
	void writeBlogContent(BlogContentsDto blogContent) throws SQLException;
	List<BlogContentsDto> listBlogContents(String blogId) throws SQLException;
	void modifyBlogContent(BlogContentsDto blogContent) throws SQLException;
	void deleteBlogContent(int blogContentsId) throws SQLException;
	List<BlogContentsDto> getAllContents() throws SQLException;
	int writeLog(Map<String, Object> log) throws SQLException;
	void increaseContentsView(int blogContentsId) throws SQLException;
	BlogContentsLikeDto readContentLike(BlogContentsLikeDto like) throws SQLException;
	void contentLike(BlogContentsLikeDto like) throws SQLException;
	void contentUnlike(BlogContentsLikeDto like) throws SQLException;
	void usergraphViewCount(int blogContentsId) throws SQLException;
	
}
