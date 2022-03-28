package com.ezen.springfeed.dto;

import lombok.Data;
import java.sql.Date;

@Data
public class MemberDto {
	private String userid;
	private String password;
	private String name;
	private String email;
	private String phone;
	private String useyn;
	private String introduce;
	private String img;
	private Date indate;
	private int isFollowing;
	
}