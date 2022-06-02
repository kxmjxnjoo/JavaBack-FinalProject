package com.ezen.springfeed.storyLike;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
public class StoryLike {
	@Id
	private Long storyLikeNum;

	@Column(name = "story_num")
	private Long storyNum;

	private String userid;
}
