package com.ezen.springfeed.faq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Faq {
    @Id
    @Column(name = "faq_num")
    private Long num;

    @Column(name = "faq_subject")
    @NotBlank(message = "제목을 입력하세요")
    private String subject;

    @Column(name = "faq_content")
    @NotBlank(message = "내용을 입력하세요")
    private String content;
}
