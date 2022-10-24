package com.dto;

import java.sql.Timestamp;

public class ReplyDto {
	private String userid;
	private String content;
	private int post_num;
	private int reply_num;
	private Timestamp reply_date;
	private String img;
	private String replyFileName;
	
	public String getReplyFileName() {
		return replyFileName;
	}
	public void setReplyFileName(String replyFileName) {
		this.replyFileName = replyFileName;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int replyNum) {
		this.reply_num = replyNum;
	}
	public Timestamp getReply_date() {
		return reply_date;
	}
	public void setReply_date(Timestamp reply_date) {
		this.reply_date = reply_date;
	}
}
