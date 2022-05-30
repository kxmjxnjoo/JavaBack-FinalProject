package com.ezen.springfeed.faq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String subject;

    @Column(name = "faq_content")
    private String content;
}
