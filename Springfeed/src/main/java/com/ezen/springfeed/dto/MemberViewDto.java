package com.ezen.springfeed.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberViewDto {
	private String userid;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String useyn;
	private String introduce;
	private Date indate;
	private String img;
	private int postNum;
	private int followerNum;
	private int followingNum;
	
}
