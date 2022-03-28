package com.ezen.springfeed.dto;

import lombok.Data;

@Data
public class FollowDto {
	private int followNum;
	private String following;
	private String follower;
}
