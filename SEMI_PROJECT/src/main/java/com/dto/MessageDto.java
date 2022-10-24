package com.dto;

import java.sql.Timestamp;

public class MessageDto {
	private int num;
	private String messageTo;
	private String messageFrom;
	private Timestamp sendDate;
	private String content;
	
	// View info
	private String fromImg;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp timestamp) {
		this.sendDate = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFromImg() {
		return fromImg;
	}

	public void setFromImg(String fromImg) {
		this.fromImg = fromImg;
	}
}
