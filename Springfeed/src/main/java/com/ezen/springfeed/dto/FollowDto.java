package com.ezen.springfeed.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class FollowDto {
	private int followNum;
	private String followed;
	private String follower;
    private Date follow_date;
}
