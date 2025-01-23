package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.security.CustomUser;
import org.zerock.service.MemberService;
import org.zerock.vo.MemberPageVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.PageDTO;
import org.zerock.vo.PageVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/*")
public class MemberController {

	@Autowired 
	private MemberService memberservice;
	
	@Autowired 
	private PasswordEncoder pwencoder;	
	
	@PostMapping("/signup")
	public  ResponseEntity<String> signup(@RequestBody MemberVO member) {
		System.out.println(member+"추가");
		member.setUserPw(pwencoder.encode(member.getUserPw()));
		int result= memberservice.registerAdmin(member,member.getUserId());
		System.out.println(member.getUserId()+"추가");
		
		System.out.println(result);
		return result ==1
				? new ResponseEntity<>("success",HttpStatus.OK)
			    :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/codeckeck")	
	@ResponseBody
	public int codeCkeck(@RequestParam("code") String code) {
		System.out.println(code+"체크");		

		int check= memberservice.ckeckCode(code);

		System.out.println(check+"결과");
		
		return check;
	}
	
	@PostMapping("/idckeck")
	@ResponseBody
	public int idCkeck(@RequestParam("id") String id) {
		System.out.println(id+"체크");		
		
		int check= memberservice.ckeckId(id);
	
		System.out.println(check+"결과");
		
		return check;
	}	
	
	@GetMapping("/memberlist")
	public String memberList(Model model) {
		
		model.addAttribute("gradelist",memberservice.getGrade());
		
	    CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	   

	    MemberPageVO page= new MemberPageVO(user.getMember().getCode());
	    
	    int memberlisttotalcount= memberservice.getMemberListTotalCount(user.getMember().getCode());
	    
	    model.addAttribute("code", user.getMember().getCode());
	    model.addAttribute("memberList", memberservice.getMemberlist(page));
	    model.addAttribute("pageMaker", new PageDTO(page,memberlisttotalcount));
	    
		return "/member/memberlist";
	}
	
	@PostMapping("/addmember")
	public  ResponseEntity<String> addMember(@RequestBody MemberVO member) {
		System.out.println(member+"추가");
		member.setUserPw(pwencoder.encode(member.getUserPw()));
		int result= memberservice.registerMember(member,member.getUserId());
		System.out.println(member.getUserId()+"추가");
		
		System.out.println(result);
		return result ==1
				? new ResponseEntity<>("success",HttpStatus.OK)
			    :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping("/memberlistload")
	public String memberListLoad(@ModelAttribute MemberPageVO page,Model model) {		
		
	    int memberlisttotalcount= memberservice.getSeachMemberListTotalCount(page);

		
		model.addAttribute("gradelist",memberservice.getGrade());
		
		model.addAttribute("code", page.getCode());
		model.addAttribute("name", page.getName());
		model.addAttribute("grade", page.getGrade());
		model.addAttribute("id", page.getId());
		
		model.addAttribute("memberList", memberservice.getMemberlist(page));
	    model.addAttribute("pageMaker", new PageDTO(page,memberlisttotalcount));
		
		return "/member/memberlist";
	}

	
}
