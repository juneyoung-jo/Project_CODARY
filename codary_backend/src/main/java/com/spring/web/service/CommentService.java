package com.spring.web.service;

import java.util.List;

import com.spring.web.dto.CommentDto;
import com.spring.web.dto.UserInfoDto;

public interface CommentService {
	
	public List<CommentDto> listComment(int blogId, int blogContentsId) throws Exception;

	public UserInfoDto getUserInfo(UserInfoDto info) throws Exception;
	public void writeComment(CommentDto comment) throws Exception;

	public void deleteComment(int commentNum)throws Exception; 

}
