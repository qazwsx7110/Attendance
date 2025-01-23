package org.zerock.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.ApprovalMapper;
import org.zerock.mapper.AttendanceMapper;
import org.zerock.security.CustomUser;
import org.zerock.service.AttendanceService;
import org.zerock.vo.AttendanceVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.SumWorkTimeVO;
import org.zerock.vo.VacationVO;
import org.zerock.vo.WorkTimeVO;
import org.zerock.vo.YearVacationVO;
import org.zerock.vo.YearsVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class AttendanceServicelmpl implements AttendanceService{

	@Autowired
	private AttendanceMapper mapper;
	
	@Override
	public List<AttendanceVO> getTodayAttendance(String userid){
	
		return mapper.todayAttendance(userid);
	}

	@Override
	@Transactional 
	public int goToWork(String userid,String code) {
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		int result=mapper.goToWork(userid,code);
		AttendanceVO todayattendance = mapper.todayAttendance(userid).get(0);
		user.getMember().setTodayAttendance(todayattendance);
		
		return result;
	}

	@Override
	public int getOffWork(String userid) {
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		int result=mapper.getOffWork(userid);
		AttendanceVO todayattendance = mapper.todayAttendance(userid).get(0);
		user.getMember().setTodayAttendance(todayattendance);
		
		return result;
	}

	@Override
	public int goToWorkHalfOffAm(String userid) {
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		int result=mapper.goToWorkHalfOffAm(userid);
		AttendanceVO todayattendance = mapper.todayAttendance(userid).get(0);
		user.getMember().setTodayAttendance(todayattendance);
		
		return result;
	};
	
	@Override
	public int goToWorkHalfOffPm(String userid) {
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		int result=mapper.goToWorkHalfOffPm(userid);
		AttendanceVO todayattendance = mapper.todayAttendance(userid).get(0);
		user.getMember().setTodayAttendance(todayattendance);
		
		return result;
	}

	@Override
	public List<AttendanceVO> getMonthAttendance(String userid, String startdate, String enddate) {
		
		List<AttendanceVO> monthattendance = mapper.monthAttendance(userid, startdate, enddate);
		
		return monthattendance;
	};
	
	@Override
	public Map<String, Object> getWorkList(String userid, String startdate, String enddate) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<WorkTimeVO> worktimelist = mapper.worktime(userid, startdate, enddate);
		SumWorkTimeVO sumworktime = mapper.sumworktime(userid, startdate, enddate).get(0);
				
		result.put("worktimelist", worktimelist);
		result.put("sumworktime", sumworktime);
		return result;
	}

	@Override
	public List<YearsVO> yearslist(Date regdate) {
		    Calendar calendar = Calendar.getInstance();
	        calendar.setTime(regdate);
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        calendar.set(Calendar.MILLISECOND, 0);
	        
	        // 변경된 날짜
	        Date resetRegDate = calendar.getTime();
	        
	        List<YearsVO> yearslist = new ArrayList<YearsVO>();
	        
	        Date startdate;
			Date enddate;
			int years=0;
			
			startdate = resetRegDate;
			Date today = new Date();
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		        
		        // Date 객체를 문자열로 변환합니다.
		        String formattedstartdate ;
		        String formattedenddate ;
			
		        
			while(startdate.before(today)) {
				YearsVO yearsvo = new YearsVO();
				formattedstartdate = formatter.format(startdate);
				
				calendar.add(Calendar.DAY_OF_YEAR, 365);
				enddate = calendar.getTime();
				
				formattedenddate = formatter.format(enddate);
				
				yearsvo.setPeriod(formattedstartdate+"- "+formattedenddate+"("+years+"년차)");
				yearsvo.setYears(years);
				
				yearslist.add(yearsvo);
				
				calendar.add(Calendar.DAY_OF_YEAR, 1);
				
				startdate=calendar.getTime();
				years++;
			}
	        
		return yearslist;
	};
	
	public Map<String, Object> vacationList(int years) {
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
		 
		Calendar calendar = Calendar.getInstance();
		
       calendar.setTime(user.getMember().getRegDate());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
		
        calendar.add(Calendar.DAY_OF_YEAR, 366*years);
		
		String startdate=formatter.format(calendar.getTime());
		
		calendar.add(Calendar.DAY_OF_YEAR, 365);
		
		String enddate=formatter.format(calendar.getTime());;
		System.out.println(startdate);
		System.out.println(enddate);
		
		
		Map<String, Object> result = new HashMap<String, Object>();
		List <VacationVO> vacationlist=mapper.getVacation(user.getMember().getUserId(), startdate, enddate);
		
		result.put("vacationlist", vacationlist);

		YearVacationVO yearvacation= new YearVacationVO();
		
		//총 휴가 개수
				int vacation;
				if(years==0) {
					vacation=12;
				}
				else if(years>=21) {
					vacation=25;
				}
				else {
					if((years%2)==1) {
						vacation=15+(years/2);
					}
					else {
						vacation=15+((years/2)-1);
					}
					
				}
				yearvacation.setVacation(vacation);
				//사용휴가일수
				double used=0;
				
				for(VacationVO va:vacationlist) {
					used+=va.getUsevacation();
				}
				yearvacation.setUsed(used);
				//남은 휴가일수
				double remaining=(double)vacation-used;
				yearvacation.setRemaining(remaining);
					
				result.put("yearvacation", yearvacation);
		
		return result;
	}
}
