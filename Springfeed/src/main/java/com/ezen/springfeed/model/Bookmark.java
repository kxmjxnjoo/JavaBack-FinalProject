package com.ezen.springfeed.model;

import java.sql.Date;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
