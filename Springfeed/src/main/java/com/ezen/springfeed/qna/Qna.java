package com.ezen.springfeed.qna;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table
@Data
public class Qna {

	@Id
	@Column(name = "qna_num")
	private int qnaNum;

	@NotBlank(message = "제목을 입력하세요!")
	@Column(name = "qna_subject")
	private String qnaSubject;

	@NotBlank(message = "내용을 입력하세요!")
	@Column(name = "qna_content")
	private String qnaContent;

	private String qna_reply;
	private String qna_id;
	private String rep;
	private Timestamp indate;
	private String qna_password;

	@Column(name = "username")
	private String userid;
}