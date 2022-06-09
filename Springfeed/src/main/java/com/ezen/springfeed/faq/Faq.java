package com.ezen.springfeed.faq;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table
@Data
public class Faq {

	@Id
	@Column(name = "faq_num")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "faq_subject")
	@NotBlank (message="제목을 입력하세요")
	private String subject;

	@Column(name = "faq_content")
	@NotBlank (message="")
	private String content;
}