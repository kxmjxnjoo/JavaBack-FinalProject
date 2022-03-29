package com.ezen.springfeed.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class MemberDto {
	
	@NotBlank(message = "아이디를 입력하세요") 
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "아이디에 사용할 수 없는 문자가 포함되어있어요!")
	private String userid;
	
	@NotBlank(message = "비밀번호를 입력하세요") 
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	private String password;
	
	@NotNull (message="이름을 입력하세요")
	@NotEmpty(message="이름을 입력하세요")
	private String name;
	
	@NotBlank (message="이메일을 입력하세요")
	@Email(message = "올바른 이메일 주소를 입력해주세요.")
	private String email;
	
	@NotNull (message="전화번호를 입력하세요")
	@NotEmpty(message="전화번호를 입력하세요")
	@Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
	private String phone;
	
	private String useyn;
	private String introduce;
	private String img;
	private Date indate;
	private int isFollowing;
	
}