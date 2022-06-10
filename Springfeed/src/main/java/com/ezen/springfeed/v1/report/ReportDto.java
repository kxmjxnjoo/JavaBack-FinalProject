package com.ezen.springfeed.v1.report;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReportDto {
	private String reporter_id;
	private String reported_id;
	private int post_num;
	private int story_num;
	private Timestamp indate;
	private String reason;
	private String report_type;
	private String handled;
	private int report_num;
}