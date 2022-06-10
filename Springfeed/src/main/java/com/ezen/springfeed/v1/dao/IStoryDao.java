package com.ezen.springfeed.v1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStoryDao {

	void addStory(HashMap<String, Object> paramMap);
	void getStory(HashMap<String, Object> paramMap);
	void deleteStory(int story_num);
	void editStory(HashMap<String, Object> paramMap);
	void getStoryPrev(HashMap<String, Object> paramMap);
	void getStoryNext(HashMap<String, Object> paramMap);
    void getStoryList(HashMap<String, Object> paramMap);
}