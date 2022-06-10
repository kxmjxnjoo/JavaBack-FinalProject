package com.ezen.springfeed.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class PostLike {
	@Id
	@Column(name = "post_like_num")
	private Long postLikeNum;

	@Column(name = "post_num")
	private Long postNum;
	private String userid;
}
