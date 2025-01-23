package org.zerock.mapper;

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

public interface ApprovalMapper {

	 public List<MemberVO> getApprovalMemberList(MemberVO membervo);

	 public String getGrade(String userid);
	 
	 public int insertApproval(@Param("approvalvo") ApprovalVO approvalvo,@Param("approvalnumber") int approvalnumber);

	 public int getApprovalNumber(String code);
	 
	 public int increaseApprovalNumber(String code);
	 
	 public int insertSign(SignVO sign);

	 public List<MemberVO> getMemberList(MemberPageVO page);
	 
	 public int getWaitApprovalListTotalCount(ApprovalPageVO page);
	 
	 public List<ApprovalVO> getWaitApprovalList(ApprovalPageVO page);
	 
	 public List<ViewApprovalVO> getViewApproval(@Param("code") String code,@Param("approvalnumber") Long approvalnumber);

	 public int applovalSign(@Param("signfilename") String signfilename,@Param("signnumber") int signnumber);

	 public int applovalTurnIncrease(@Param("code") String code,@Param("approvalnumber") int approvalnumber);

	 public int applovalcomplete(@Param("code") String code,@Param("approvalnumber") int approvalnumber);

	 public int applovalreject(@Param("code") String code,@Param("approvalnumber") int approvalnumber);
	 
	 public List<ApprovalVO> getCompleteApprovalList(CompleteApprovalPageVO page);
	 
	 public int getCompleteApprovalListTotalCount(CompleteApprovalPageVO page);
	 
     public List<ApprovalVO> getRejectApprovalList(RejectApprovalPageVO page);
	 
	 public int getRejectApprovalListTotalCount(RejectApprovalPageVO page);

	 public int insertVacation(@Param("userId") String userid, @Param("attDate") Date attdate,@Param("code") String code,@Param("vacation") String vacation);

	 public int insertHalfVacation(@Param("userId") String userid, @Param("attDate") Date attdate,@Param("code") String code,@Param("vacation") String vacation);

}
