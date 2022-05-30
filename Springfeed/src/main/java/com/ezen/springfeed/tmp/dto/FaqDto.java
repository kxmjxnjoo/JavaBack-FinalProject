package com.ezen.springfeed.tmp.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class FaqDto {
	
	private int faq_num;
	@NotBlank (message="제목을 입력하세요")
	private String faq_subject;
	@NotBlank (message="")
	private String faq_content;
}