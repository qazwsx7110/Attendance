package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.vo.AttendanceDownloadVO;
import org.zerock.vo.AttendanceVO;

public interface ExcelMapper {

	public List<AttendanceDownloadVO> attendanceDownload(@Param("userId") String userid,@Param("startDate") String startdate,@Param("endDate") String enddate);
	
}
