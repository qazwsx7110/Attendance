package org.zerock.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.zerock.vo.AttendanceVO;
import org.zerock.vo.GradeVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.VacationVO;
import org.zerock.vo.YearsVO;

public interface AttendanceService {

	public List<AttendanceVO> getTodayAttendance(String userid);
	
	public int goToWork(String userid,String code);
	
	public int goToWorkHalfOffAm(String userid);
	
	public int goToWorkHalfOffPm(String userid);
	
	public int getOffWork(String userid);
	
	public List<AttendanceVO> getMonthAttendance(String userid,String startdate,String enddate);

	public Map<String, Object> getWorkList(String userid, String startdate, String enddate);

	public List<YearsVO> yearslist(Date regdate);
	
	public Map<String, Object> vacationList(int years);
}
