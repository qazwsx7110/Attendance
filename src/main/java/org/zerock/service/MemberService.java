package org.zerock.service;

import java.util.ArrayList;
import java.util.List;

import org.zerock.vo.GradeVO;
import org.zerock.vo.MemberPageVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.PageVO;

public interface MemberService {

	public int ckeckCode(String code);
	
	public int ckeckId(String id);
	
	public int registerAdmin(MemberVO member,String id);
	
	public int registerMember(MemberVO member,String id);
	
	public MemberVO get(int m_no);
	
	public List<GradeVO> getGrade();
	 
	public List<MemberVO> getMemberlist(MemberPageVO page);
	
	public int getMemberListTotalCount(String code);
	
	public int getSeachMemberListTotalCount(MemberPageVO page);
}
