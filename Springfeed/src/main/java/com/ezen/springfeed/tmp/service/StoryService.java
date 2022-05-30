package com.ezen.springfeed.tmp.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springfeed.tmp.dao.IStoryDao;

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

	public void editStory(HashMap<String, Object> paramMap) {
		sdao.editStory(paramMap);
	}

	public void getStoryPrev(HashMap<String, Object> paramMap) {
		sdao.getStoryPrev(paramMap);
	}

	public void getStoryNext(HashMap<String, Object> paramMap) {
		sdao.getStoryNext(paramMap);
	}

    public void getStoryList(HashMap<String, Object> paramMap) {
		sdao.getStoryList(paramMap);
    }
}
