package com.ezen.springfeed.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springfeed.dao.IStoryDao;

@Service
public class StoryService {

	@Autowired
	IStoryDao sdao;
	
	public void addStory(HashMap<String, Object> paramMap) {
		sdao.addStory(paramMap);
	}

	public void getStory(HashMap<String, Object> paramMap) {
		sdao.getStory(paramMap);
	}

	public void deleteStory(int story_num) {
		sdao.deleteStory(story_num);
	}

}
