package com.ezen.springfeed.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class QnaDto {

	private int qna_num;
	private String qna_subject;
	private String qna_content;
	private String qna_reply;
	private String qna_id;
	private String rep;
	private Timestamp indate;
	private String qna_password;
	private String username;
}