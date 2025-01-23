package org.zerock.service;

import java.util.List;

import org.zerock.vo.AttendanceDownloadVO;
import org.zerock.vo.AttendanceVO;
import org.zerock.vo.SumWorkTimeVO;
import org.zerock.vo.WorkTimeVO;

public interface ExcelService {
	
	public List<AttendanceDownloadVO> getExelAttendance(String userid,String startdate,String enddate);

	public List<WorkTimeVO> getExelWorkTime(String userid,String startdate,String enddate);
	
	public SumWorkTimeVO getExelSumWorkTime(String userid,String startdate,String enddate);
}
