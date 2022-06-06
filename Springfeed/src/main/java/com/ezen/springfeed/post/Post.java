package com.ezen.springfeed.post;
import java.sql.Timestamp;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Post {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
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