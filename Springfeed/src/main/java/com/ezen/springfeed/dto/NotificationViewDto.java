package com.ezen.springfeed.dto;
import java.sql.Date;

import lombok.Data;

@Data
public class NotificationViewDto {
	private String userTo;
	private String userFrom;
	private Date createDate;
	private String memberImg;
	private int notiType;
	
	private String datePassed;
	
}
