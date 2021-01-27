package com.spring.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.CommentDto;
import com.spring.web.dto.CommentToLikeDto;
import com.spring.web.dto.UserInfoDto;

@Mapper
public interface CommentDao {

	List<CommentDto> listComment(Map<String, Object> map) throws Exception;

	UserInfoDto getUserInfo(UserInfoDto info) throws Exception;

	void writeComment(CommentDto comment) throws Exception;

	void deleteComment(int commentNum) throws Exception;

	void modifyComment(CommentDto comment) throws Exception;

	CommentToLikeDto getCommentLike(CommentToLikeDto ctl) throws Exception;

	void upComment(CommentToLikeDto ctl) throws Exception;

	void commentLike(CommentToLikeDto ctl) throws Exception;

	void downComment(CommentToLikeDto ctl) throws Exception;

	int commentLikeCancle(CommentToLikeDto ctl) throws Exception;

}
