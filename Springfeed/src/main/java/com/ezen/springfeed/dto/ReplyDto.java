package com.ezen.springfeed.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReplyDto {
	private String userid;
	private String content;
	private int post_num;
	private int reply_num;
	private Timestamp reply_date;
	private String img;
	private String replyFileName;
}
