package com.ezen.springfeed.v1.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface IUtilDao {
    void getMemberSearch(HashMap<String, Object> paramMap);
    void getPostSearch(HashMap<String, Object> paramMap);
	void addStoryReport(HashMap<String, Object> paramMap);
	void addPostReport(HashMap<String, Object> paramMap);
	void addUserReport(HashMap<String, Object> paramMap);
    void exploreFeed(HashMap<String, Object> paramMap);
}