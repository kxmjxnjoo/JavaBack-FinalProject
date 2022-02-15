package com.dto;

import java.sql.Date;

public class NotificationViewDto {
	private String userTo;
	private String userFrom;
	private Date createDate;
	private String memberImg;
	private int notiType;
	
	private String datePassed;
	
	// 1 : follow
	
	public String getDatePassed() {
		return datePassed;
	}
	public void setDatePassed(String datePassed) {
		this.datePassed = datePassed;
	}
	// 2 : like
	private String postImg;
	// 3 : reply
	private String replyContent;
	
	
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMemberImg() {
		return memberImg;
	}
	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}
	public int getNotiType() {
		return notiType;
	}
	public void setNotiType(int notiType) {
		this.notiType = notiType;
	}
	public String getPostImg() {
		return postImg;
	}
	public void setPostImg(String postImg) {
		this.postImg = postImg;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
}
