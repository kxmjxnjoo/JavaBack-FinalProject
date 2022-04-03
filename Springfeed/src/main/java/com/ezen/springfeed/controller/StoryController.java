package com.ezen.springfeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springfeed.service.StoryService;

@Controller
public class StoryController {

	@Autowired
	StoryService ss;
	
	
	@RequestMapping("/story/add/form") 
	public String storyUploadForm() {
		return "post/addStroy";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}