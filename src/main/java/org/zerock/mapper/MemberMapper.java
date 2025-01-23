package org.zerock.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.vo.GradeVO;
import org.zerock.vo.MemberPageVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.PageVO;

public interface MemberMapper {

	
	public MemberVO read(String userid);

	public int ckeckCode(String code);
	
	public int ckeckId(String code);

	public int insertMember(MemberVO membervo);

	public int insertAuthadmin(String userid);
	
	public int insertAuthmember(String userid);
	
	public int insertApprovalNumber(String code);
	
	public List<GradeVO> getGrade();
	
    public List<MemberVO> getMemberList(MemberPageVO page);
		
    public int getMemberListTotalCount(String code);
    
	public void readMember(int m_no);
		
	public int idcode(String code);
	
	public int getSeachMemberListTotalCount(MemberPageVO page);
	
	
}
