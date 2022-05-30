package com.ezen.springfeed.tmp.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class QnaDto {
	
	private int qna_num;
	@NotBlank(message = "제목을 입력하세요!") 
	private String qna_subject;
	@NotBlank(message = "내용을 입력하세요!") 
	private String qna_content;
	private String qna_reply;
	private String qna_id;
	private String rep;
	private Timestamp indate;
	private String qna_password;
	private String username;
}