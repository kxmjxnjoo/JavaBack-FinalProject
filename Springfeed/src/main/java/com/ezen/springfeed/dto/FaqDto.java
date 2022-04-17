package com.ezen.springfeed.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class FaqDto {
	
	private int faq_num;
	@NotBlank (message="제목을 입력하세요")
	private String faq_subject;
	@NotBlank (message="내용을 입력하세요")
	private String faq_content;
}