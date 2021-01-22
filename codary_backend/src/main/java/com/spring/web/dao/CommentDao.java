package com.spring.web.dao;

import java.util.List;
import java.util.Map;

import com.spring.web.dto.CommentDto;
import com.spring.web.dto.UserInfoDto;

public interface CommentDao {

	List<CommentDto> listComment(Map<String, Integer> map) throws Exception;

	UserInfoDto getUserInfo(UserInfoDto info) throws Exception;

}
