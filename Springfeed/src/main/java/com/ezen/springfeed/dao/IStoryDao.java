package com.ezen.springfeed.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IStoryDao {

	void addStory(HashMap<String, Object> paramMap);
	void getStory(HashMap<String, Object> paramMap);
	void deleteStory(int story_num);
	void getStoryPrevNext(HashMap<String, Object> paramMap);
	void editStory(HashMap<String, Object> paramMap);

}