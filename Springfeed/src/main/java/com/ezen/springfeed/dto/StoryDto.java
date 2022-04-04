package com.ezen.springfeed.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StoryDto {
	
	private int story_num; 
	private String story_img;
	private String userid;
	private String user_img;
	private Timestamp create_date;
	private String story_content;
	private String fontColor;
	
}
