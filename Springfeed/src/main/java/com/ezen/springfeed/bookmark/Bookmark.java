package com.ezen.springfeed.bookmark;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table
@Data
public class Bookmark {
	@Id
	Integer num;
	String userid;
	int postNum;
	Date saveDate;
}
