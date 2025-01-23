package org.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.zerock.mapper.AttendanceMapper;
import org.zerock.mapper.MemberMapper;
import org.zerock.service.AttendanceService;
import org.zerock.vo.AttendanceVO;
import org.zerock.vo.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private AttendanceMapper attendanceMapper;
	
	@Autowired 
	private AttendanceService attendanceservice;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		log.warn("Load User :"+ userName);
		
		MemberVO vo = memberMapper.read(userName);
		
		if(attendanceMapper.todayAttendance(userName).size()>0) {
		AttendanceVO todayattendance = attendanceMapper.todayAttendance(userName).get(0);		
		vo.setTodayAttendance(todayattendance);		
		}
	
		return vo==null ? null : new CustomUser(vo);
	}

}
