package com.ezen.springfeed.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MemberDto {
	
	@NotNull (message="아이디를 입력하세요")
	@NotEmpty(message="아이디를 입력하세요")
	private String userid;
	
	@NotNull (message="비밀번호를 입력하세요")
	@NotEmpty(message="비밀번호를 입력하세요")
	private String password;
	
	@NotNull (message="이름을 입력하세요")
	@NotEmpty(message="이름을 입력하세요")
	private String name;
	
	@NotNull (message="이메일을 입력하세요")
	@NotEmpty(message="이메일을 입력하세요")
	private String email;
	
	@NotNull (message="전화번호를 입력하세요")
	@NotEmpty(message="전화번호를 입력하세요")
	private String phone;
	
	private String useyn;
	private String introduce;
	private String img;
	private Date indate;
	private int isFollowing;
	
}