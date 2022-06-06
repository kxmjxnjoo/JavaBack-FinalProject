package com.ezen.springfeed.message;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class Message {
	private int num;
	private String messageTo;
	private String messageFrom;
	private Timestamp sendDate;
	private String content;
}
