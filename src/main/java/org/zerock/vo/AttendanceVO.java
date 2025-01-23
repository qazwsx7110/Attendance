package org.zerock.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AttendanceVO {

	private int attNo;
	private String userId;
	private Date startTime;
	private Date endTime;
	private String state;
	private Date attDate;
	private String code;
	private String vacation;
}
