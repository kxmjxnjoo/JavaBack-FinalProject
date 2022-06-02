package com.ezen.springfeed.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class BookmarkDto {
	int num;
	String userid;
	int postNum;
	Date saveDate;
}
