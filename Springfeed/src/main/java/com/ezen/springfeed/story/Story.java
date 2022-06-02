package com.ezen.springfeed.story;

import java.sql.Timestamp;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

@Data
public class Story {

	@Id
	@Column(name = "story_num")
	private Long storyNum;

	@Column(name = "story_img")
	private String storyImg;

	private String userid;
	@Column(name = "user_img")
	private String userImg;

	@Column(name = "create_date")
	private Timestamp createDate;

	@Column(name = "story_content")
	private String storyContent;

	private String fontColor;
	private String useyn;
}
