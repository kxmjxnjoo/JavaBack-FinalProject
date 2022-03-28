package com.ezen.springfeed.dto;

import lombok.Data;

@Data
public class FollowViewDto {
	private String following;
	private String follower;
	
	private String followingImg;
	private String followingName;
	
	private int isStoryPresent;
	
}
