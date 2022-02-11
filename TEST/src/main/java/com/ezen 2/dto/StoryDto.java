package com.ezen.dto;

import java.sql.Date;

public class StoryDto {
	private int storyNum;
	private String img;
	private String userid;
	private Date createDate;
	
	
	public int getStoryNum() {
		return storyNum;
	}
	public void setStoryNum(int storyNum) {
		this.storyNum = storyNum;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
