package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.vo.AttendanceVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.SumWorkTimeVO;
import org.zerock.vo.VacationVO;
import org.zerock.vo.WorkTimeVO;

public interface AttendanceMapper {

	public List<AttendanceVO> todayAttendance(String userId);
	
	public int goToWork(@Param("userId") String userid,@Param("code") String code);
	
	public int goToWorkHalfOffAm(String userId);
	
	public int goToWorkHalfOffPm(String userId);
	
	public int getOffWork(String userId);
	
	public List<AttendanceVO> monthAttendance(@Param("userId") String userid,@Param("startDate") String startdate,@Param("endDate") String enddate);
	
	public List<WorkTimeVO> worktime(@Param("userId") String userid,@Param("startDate") String startdate,@Param("endDate") String enddate);
	
	public List<SumWorkTimeVO> sumworktime(@Param("userId") String userid,@Param("startDate") String startdate,@Param("endDate") String enddate);

	public List<VacationVO> getVacation(@Param("userId") String userid,@Param("startDate") String startdate,@Param("endDate") String enddate);
}
