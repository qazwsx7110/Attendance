package org.zerock.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.mapper.AttendanceMapper;
import org.zerock.mapper.ExcelMapper;
import org.zerock.service.AttendanceService;
import org.zerock.service.ExcelService;
import org.zerock.vo.AttendanceDownloadVO;
import org.zerock.vo.AttendanceVO;
import org.zerock.vo.SumWorkTimeVO;
import org.zerock.vo.WorkTimeVO;

import lombok.extern.log4j.Log4j;


@Log4j
@Service
public class ExcelServicelmpl implements ExcelService{
	
	@Autowired
	private ExcelMapper exelmapper;
	
	@Autowired
	private AttendanceMapper attendancemapper;
	
	public List<AttendanceDownloadVO> getExelAttendance(String userid,String startdate,String enddate) {
		return exelmapper.attendanceDownload(userid, startdate, enddate);
	}

	@Override
	public List<WorkTimeVO> getExelWorkTime(String userid, String startdate, String enddate) {
		// TODO Auto-generated method stub
		return attendancemapper.worktime(userid, startdate, enddate);
	}

	@Override
	public SumWorkTimeVO getExelSumWorkTime(String userid, String startdate, String enddate) {
		// TODO Auto-generated method stub
		return attendancemapper.sumworktime(userid, startdate, enddate).get(0);
	}
}
