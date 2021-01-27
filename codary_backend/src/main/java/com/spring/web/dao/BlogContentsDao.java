package com.spring.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogContentsDto;

@Mapper
public interface BlogContentsDao {
	
	BlogContentsDto getContent(int blogContentsId) throws SQLException;
	void writeBlogContent(BlogContentsDto blogContent) throws SQLException;
	List<BlogContentsDto> listBlogContents(String blogId) throws SQLException;
	void modifyBlogContent(BlogContentsDto blogContent) throws SQLException;
	void deleteBlogContent(int blogContentsId) throws SQLException;

}
