package com.ezen.springfeed.v2.reply;

import java.sql.Timestamp;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
@Data
public class Reply {
	@Id
	@Column(name = "reply_num")
	private Long id;

	private String userid;
	private String content;
	@Column(name = "post_num")
	private int postNum;
	@Column(name = "reply_date")
	private Timestamp replyDate;
	private String img;
	private String replyFileName;
}
