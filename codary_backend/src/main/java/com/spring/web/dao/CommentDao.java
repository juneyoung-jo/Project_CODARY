package com.spring.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.CommentDto;
import com.spring.web.dto.UserInfoDto;

@Mapper
public interface CommentDao {

	List<CommentDto> listComment(Map<String, Integer> map) throws Exception;

	UserInfoDto getUserInfo(UserInfoDto info) throws Exception;

	void writeComment(CommentDto comment) throws Exception;

	void deleteComment(int commentNum) throws Exception;

	void modifyComment(CommentDto comment) throws Exception;

}
