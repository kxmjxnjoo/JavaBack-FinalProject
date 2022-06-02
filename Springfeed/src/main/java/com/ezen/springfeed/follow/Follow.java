package com.ezen.springfeed.follow;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table
@Data
public class Follow {
	@Id
	private Long followNum;
	private String followed;
	private String follower;
	@Column(name = "follow_date")
    private Date followDate;
}
