package com.dto;

import java.sql.Timestamp;

public class StoryDto {
	private int story_num; 
	private String story_img;
	private String userid;
	private String user_img;
	private Timestamp create_date;
	private String content;
	
	public int getStory_num() {
		return story_num;
	}
	public void setStory_num(int story_num) {
		this.story_num = story_num;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStory_img() {
		return story_img;
	}
	public void setStory_img(String story_img) {
		this.story_img = story_img;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	
}
