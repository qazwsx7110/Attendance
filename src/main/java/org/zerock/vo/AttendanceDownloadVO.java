package org.zerock.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AttendanceDownloadVO {

	private String attDate;
	private String startTime;
	private String endTime;
	private String state;
}
