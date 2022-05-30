package com.ezen.springfeed.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id
	@Column
	private Long postNum;

	@Column(name = "post_img")
	private String postImg;
	private String content;
	private String address;
	private String userid;
	@Column(name="create_date")
	private Timestamp createDate;
	private String user_img;
	private int likeCount;
	private int replyCount;

	private int isLiked;
	private int isSaved;
}