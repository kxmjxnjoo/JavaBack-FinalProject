package com.ezen.springfeed.dto;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostDto {
	private int postNum;
	private String post_img;
	private String content;
	private String address;
	private String userid;
	private Timestamp create_date;
	private String user_img;
	private int likeCount;
	private int replyCount;

	private int isLiked;
	private int isSaved;
}