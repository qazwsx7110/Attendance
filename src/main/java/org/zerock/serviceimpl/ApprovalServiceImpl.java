package org.zerock.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.ApprovalMapper;
import org.zerock.mapper.MemberMapper;
import org.zerock.security.CustomUser;
import org.zerock.service.ApprovalService;
import org.zerock.vo.ApprovalPageVO;
import org.zerock.vo.ApprovalVO;
import org.zerock.vo.CompleteApprovalPageVO;
import org.zerock.vo.MemberPageVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.RejectApprovalPageVO;
import org.zerock.vo.SignVO;
import org.zerock.vo.ViewApprovalVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private ApprovalMapper mapper;
		
	@Override
	public List<MemberVO> getApprovalMemberList(MemberVO membervo) {
		// TODO Auto-generated method stub
		return mapper.getApprovalMemberList(membervo);
	}

	@Override
	public String getgrade(String userid) {
		// TODO Auto-generated method stub
		return mapper.getGrade(userid);
	}

	@Override
	@Transactional 
	public int insertapproval(ApprovalVO approvalvo,String[] approvalhierarchy,String code) {
		int approvalnumber =mapper.getApprovalNumber(code);
		System.out.println("글번호"+approvalnumber);
		int result2=mapper.increaseApprovalNumber(code);
		int result=mapper.insertApproval(approvalvo,approvalnumber);
		int result3=1;
		for (int i = 0; i < approvalhierarchy.length; i++) {
			SignVO sign=new SignVO();
			sign.setCode(code);
			sign.setApprovalNo(approvalnumber);
			sign.setApprovar(approvalhierarchy[i]);
			sign.setOrderNo(i);
			result3*=mapper.insertSign(sign);
			}
							
		return result*result2*result3;
	}

	@Override
	public int insertsign(SignVO sign) {
		// TODO Auto-generated method stub
		return mapper.insertSign(sign);
	}

	@Override
	public int getWaitApprovalListTotalCount(ApprovalPageVO page) {
		// TODO Auto-generated method stub
		return mapper.getWaitApprovalListTotalCount(page);
	}

	@Override
	public List<ApprovalVO> getWaitApprovalList(ApprovalPageVO page) {
		// TODO Auto-generated method stub
		return mapper.getWaitApprovalList(page);
	}

	@Override
	public List<ViewApprovalVO> getViewApproval(String code, Long approvalnumber) {
		// TODO Auto-generated method stub
		return mapper.getViewApproval(code, approvalnumber);
	}

	@Override
	@Transactional 
	public int approval(String signfilename,int signnumber,String code,int approvalnumber,String complete,String vacation,String startdate,String enddate,String writer) {
		int result1=mapper.applovalSign(signfilename,signnumber);
		int result2=mapper.applovalTurnIncrease(code, approvalnumber);
		int result3=1;
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		 SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH); // 날짜 형식에 맞게 설정
		 Date dstartdate = null ;
		 Date denddate = null ;
		 System.out.println(startdate);
		 System.out.println(enddate);
		 try {
			dstartdate = sdf.parse(startdate);
			System.out.println(dstartdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 문자열을 Date 객체로 변환

		 try {
				denddate = sdf.parse(enddate);
				System.out.println(denddate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 문자열을 Date 객체로 변환
		 
		if(complete!=null) {
			result3=mapper.applovalcomplete(code, approvalnumber);	
			System.out.println(vacation);
			if(vacation.equals("반차(오전)")) {
				mapper.insertHalfVacation(writer, dstartdate, user.getMember().getCode(),"반차(오전)");
			}
			if(vacation.equals("반차(오후)")) {
				mapper.insertHalfVacation(writer, dstartdate, user.getMember().getCode(),"반차(오후)");			
			}
			if(vacation.equals("연차")) {
				Calendar calendar = Calendar.getInstance();
		        calendar.setTime(dstartdate);  // dstartdate를 Calendar에 설정

		        // dstartdate의 일(day)만 추출
		        int dstartdateday = calendar.get(Calendar.DAY_OF_MONTH);
		        System.out.println("시작일:"+dstartdateday);
		        
		        calendar.setTime(denddate);
		        int denddateday = calendar.get(Calendar.DAY_OF_MONTH);
		        System.out.println("종료일:"+denddateday);
		        
		        int during;
		        during=denddateday-dstartdateday+1;
		        System.out.println("휴가일수:"+during);
		        
		        for(int i=0;i<during;i++) {
		        	calendar.setTime(dstartdate);
		        	calendar.add(Calendar.DAY_OF_MONTH, i);

		            // 새로운 날짜 얻기
		            Date newDate = calendar.getTime();
		        	mapper.insertVacation(writer, newDate, user.getMember().getCode(), "연차");
		        	
		        }
				
			}

		    
		}
		
		 System.out.println(result1);
	     System.out.println(result2);
		return result1*result2*result3;
	}

	@Override
	public int reject(String code, int approvalnumber) {
		
		int result1=mapper.applovalreject(code, approvalnumber);
		return result1;
	}

	@Override
	public List<ApprovalVO> getCompleteApprovalList(CompleteApprovalPageVO page) {
		// TODO Auto-generated method stub
		
		return mapper.getCompleteApprovalList(page);
	}

	@Override
	public int getCompleteApprovalListTotalCount(CompleteApprovalPageVO page) {
		// TODO Auto-generated method stub
		return mapper.getCompleteApprovalListTotalCount(page);
	}

	@Override
	public List<ApprovalVO> getRejectApprovalList(RejectApprovalPageVO page) {
		// TODO Auto-generated method stub
		return mapper.getRejectApprovalList(page);
	}

	@Override
	public int getRejectApprovalListTotalCount(RejectApprovalPageVO page) {
		// TODO Auto-generated method stub
		return mapper.getRejectApprovalListTotalCount(page);
	}
	
	
	
}
