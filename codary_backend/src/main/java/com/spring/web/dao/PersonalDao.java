package com.spring.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.web.dto.BlogContentsDto;

@Mapper
public interface PersonalDao {
	public List<BlogContentsDto> selectPersonal(String uid);
}
