package com.ezen.dto;

import java.sql.Date;

public class NotificationDto {
	private String userTo;
	private String userFrom;
	private String content;
	private int postNum;
	private Date createDate;
	private int notiType;
	
	
	
	public int getNotiType() {
		return notiType;
	}
	public void setNotiType(int notiType) {
		this.notiType = notiType;
	}
	public String getUserTo() {
		return userTo;
	}
	public void setUserTo(String userTo) {
		this.userTo = userTo;
	}
	public String getUserFrom() {
		return userFrom;
	}
	public void setUserFrom(String userFrom) {
		this.userFrom = userFrom;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
