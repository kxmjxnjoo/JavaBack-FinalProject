package com.ezen.springfeed.tmp.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class PostViewDto {
	private int postNum;
	private String postImg;
	private String content;
	private String address;
	private String userid;
	private String userImg;
	private int likes;
	private Date createDate;
	private int isLikedByUser;
	private int likeCount;
	private int replyCount;
	
	private int isSaved;
}
