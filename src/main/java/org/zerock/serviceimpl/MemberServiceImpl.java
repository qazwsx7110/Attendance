package org.zerock.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.MemberMapper;
import org.zerock.service.MemberService;
import org.zerock.vo.GradeVO;
import org.zerock.vo.MemberPageVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.PageVO;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;
	
	@Transactional 
    public int registerAdmin(MemberVO member,String userid) {
    	
        int result= mapper.insertMember(member);
        int result2=mapper.insertAuthadmin(userid);
        int result3=mapper.insertApprovalNumber(member.getCode());	
    	return result*result2*result3;
    };
	
    @Transactional 
    public int registerMember(MemberVO member,String userid) {
    	
        int result= mapper.insertMember(member);
        int result2=mapper.insertAuthmember(userid);
        
    	return result*result2;
    };
    
	public MemberVO get(int m_no) {
		return null;
		
	}

	@Override
	public int ckeckCode(String code) {
		// TODO Auto-generated method stub
		return mapper.ckeckCode(code);
	}

	@Override
	public int ckeckId(String id) {
		// TODO Auto-generated method stub
		return mapper.ckeckId(id);
	}

	

	@Override
	public List<GradeVO> getGrade() {
		// TODO Auto-generated method stub
		return mapper.getGrade();
	}

	@Override
	public List<MemberVO> getMemberlist(MemberPageVO page) {
		// TODO Auto-generated method stub
		return mapper.getMemberList(page);
	}

	@Override
	public int getMemberListTotalCount(String code) {
		// TODO Auto-generated method stub
		return mapper.getMemberListTotalCount(code);
	}
                         
	@Override
	public int getSeachMemberListTotalCount(MemberPageVO page) {
		// TODO Auto-generated method stub
		return mapper.getSeachMemberListTotalCount(page);
	};
	
	
}
