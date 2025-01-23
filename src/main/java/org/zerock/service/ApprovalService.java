package org.zerock.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.vo.ApprovalPageVO;
import org.zerock.vo.ApprovalVO;
import org.zerock.vo.CompleteApprovalPageVO;
import org.zerock.vo.MemberPageVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.RejectApprovalPageVO;
import org.zerock.vo.SignVO;
import org.zerock.vo.ViewApprovalVO;

public interface ApprovalService {
	public List<MemberVO> getApprovalMemberList(MemberVO membervo);
	
	public String getgrade(String userid);
	
	public int insertapproval(ApprovalVO approvalvo,String[] approvalhierarchy,String code);
	
	 public int insertsign(SignVO sign);
	 
	 public List<ApprovalVO> getWaitApprovalList(ApprovalPageVO page);
	 
	 public int getWaitApprovalListTotalCount(ApprovalPageVO page);
	 
	 public List<ViewApprovalVO> getViewApproval(String code,Long approvalnumber);

	 public int approval(String signfilename,int signnumber,String code,int approvalnumber,String complete,String state,String startdate,String enddate,String writer);

	 public int reject(String code,int approvalnumber);
	 
	 public List<ApprovalVO> getCompleteApprovalList(CompleteApprovalPageVO  page);
	 
	 public int getCompleteApprovalListTotalCount(CompleteApprovalPageVO  page);

     public List<ApprovalVO> getRejectApprovalList(RejectApprovalPageVO  page);
	 
	 public int getRejectApprovalListTotalCount(RejectApprovalPageVO  page);

	
} 
