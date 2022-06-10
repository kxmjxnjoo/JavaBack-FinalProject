package com.ezen.springfeed.v2.post;
import java.sql.Timestamp;

import com.ezen.springfeed.v2.member.Member;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Post {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// POST table column
	@Column(name = "post_img")
	private String postImg;
	private String content;
	private String address;
	@Column(name="create_date")
	private Timestamp createDate;

	// Foreign key from MEMBER
	@ManyToOne
	private Member member;

	private String userid;
	private String user_img;

	// Count from POSTLIKE
	private int likeCount;

	// Count from REPLY
	private int replyCount;
}