package com.spring.web.dao;

import org.springframework.stereotype.Repository;

import com.spring.web.dto.Testdb;

@Repository
public interface TestdbDao {

	public Testdb test();
	
}
