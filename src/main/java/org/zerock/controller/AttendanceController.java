package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.security.CustomUser;
import org.zerock.service.ApprovalService;
import org.zerock.service.AttendanceService;
import org.zerock.vo.ApprovalVO;
import org.zerock.vo.AttendanceVO;
import org.zerock.vo.MemberPageVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.PageDTO;
import org.zerock.vo.VacationVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/attendance/*")
public class AttendanceController {
	
	@Autowired 
	private AttendanceService attendanceservice;
	
	private Scheduler scheduler;
	
	@PostMapping("/attendance")
	@ResponseBody
	public String attendance() {
		System.out.println("출석");
		System.out.println(scheduler.onmaintainance);
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		 String result = null;
		if(scheduler.onmaintainance) {
			result="maintainance";
			return result;
		
		}
		
		 if(user.getMember().getTodayAttendance()==null) {
			 System.out.println("출근");
			 MemberVO member= new MemberVO();
			 member = user.getMember();
			 attendanceservice.goToWork(member.getUserId(),member.getCode());
			result="gotowork";
		}
		 else if(user.getMember().getTodayAttendance().getVacation()!=null) {
			
			 if(user.getMember().getTodayAttendance().getVacation().equals("반차(오전)")  &&(user.getMember().getTodayAttendance().getState()==null)) {
			 System.out.println("반차(오전)출근");
			 MemberVO member= new MemberVO();
			 member = user.getMember();
			 attendanceservice.goToWorkHalfOffAm(member.getUserId());
			 result="gotowork";
			 }
			 
			 else if(user.getMember().getTodayAttendance().getVacation().equals("반차(오후)")  &&(user.getMember().getTodayAttendance().getState()==null)) {
				 System.out.println("반차(오후)출근");
				 MemberVO member= new MemberVO();
				 member = user.getMember();
				 attendanceservice.goToWorkHalfOffPm(member.getUserId());
				 result="gotowork";
			 }
			 
			 else {
				 
				 AttendanceVO todayAttendance=user.getMember().getTodayAttendance();			
					if(todayAttendance.getState().equals("정상출근")||todayAttendance.getState().equals("지각")) {			
						System.out.println("퇴근");
						String userid = user.getMember().getUserId();
						
						attendanceservice.getOffWork(userid);
						result="getoffwork";
					}
			 }
			 
		 }
		 
		 else{
			
			AttendanceVO todayAttendance=user.getMember().getTodayAttendance();			
			if(todayAttendance.getState().equals("정상출근")||todayAttendance.getState().equals("지각")) {			
				System.out.println("퇴근");
				String userid = user.getMember().getUserId();
				
				attendanceservice.getOffWork(userid);
				result="getoffwork";
			}
            
		}
		
		return result;
	}
	
	@GetMapping("/myattendance")
	public String myAttendance(Model model) {
		
		
		return "/attendance/myattendance";
	}
	
	@GetMapping("/myworktime")
	public String myWorkTime(Model model) {
		
		
		return "/attendance/myworktime";
	}
	
	@GetMapping("/myvacation")
	public String myVacation(Model model) {
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		model.addAttribute("yearsList", attendanceservice.yearslist(user.getMember().getRegDate()));
		
		return "/attendance/myvacation";
	}
	
	@GetMapping("/mychart")
	public String myChart(Model model) {
		
		
		return "/attendance/mychart";
	}
	
	@PostMapping("/attendancelist")
	@ResponseBody
	public List<AttendanceVO> attendanceList(String userid,String startdate,String enddate){
		
		List<AttendanceVO> todayAttendance =attendanceservice.getMonthAttendance(userid, startdate, enddate);
		System.out.println(userid+":"+startdate+":"+enddate);
		return todayAttendance;
	} 
	
	@PostMapping("/worklist")
	@ResponseBody
	public Map<String, Object> workList(String userid,String startdate,String enddate){
		
		System.out.println(userid+":"+startdate+":"+enddate);
	
		return attendanceservice.getWorkList(userid, startdate, enddate);
	} 
	@PostMapping("/vacationlist")
	@ResponseBody
	public Map<String, Object> vacationList(String userid,int years){
		
		
		System.out.println("아이디:"+userid+"선택년수:"+years);
		return attendanceservice.vacationList(years);
	} 
	
}
