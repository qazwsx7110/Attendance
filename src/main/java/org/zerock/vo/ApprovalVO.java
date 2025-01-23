package org.zerock.vo;

import java.sql.Clob;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ApprovalVO {

	private String code;
	private int approvalNo;
	
	private Date reportingDate;
	private String writer;
	private String writerName;
	private String writerGrade;
	private String title;
	private String vacation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	private String state;
	
	private String content;
	private int turn;
}
