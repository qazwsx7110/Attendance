package org.zerock.vo;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
public class MemberVO {
	
	private String userId;
	private String userPw;
	private String userName;
	private boolean enabled;
	private String code;
	private String grade;
	private int rank;
	private int attendance;
	private AttendanceVO todayAttendance;
	
	private Date regDate;
	private List<AuthVO> authList;
}
