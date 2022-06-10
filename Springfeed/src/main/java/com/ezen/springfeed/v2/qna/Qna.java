package com.ezen.springfeed.v2.qna;

import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table
@Data
public class Qna {

	@Id
	@Column(name = "qna_num")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long qnaNum;

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
	@NotNull(message = "로그인 해 주세요!")
	private String userid;
}