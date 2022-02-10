package com.ezen.dto;

import java.sql.Date;

public class PostDto {
	private int postNum;
	private String img;
	private String content;
	private String address;
	private String userid;
	private Date createDate;
	
	
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getCreate_date() {
		return createDate;
	}
	public void setCreate_date(Date create_date) {
		this.createDate = create_date;
	}
}
