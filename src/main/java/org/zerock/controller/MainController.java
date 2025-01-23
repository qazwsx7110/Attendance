package org.zerock.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.MemberService;
import org.zerock.vo.GradeVO;
import org.zerock.vo.MemberVO;

import lombok.extern.log4j.Log4j;


@Controller
public class MainController {

	@Autowired 
	private MemberService memberservice;
	
	@GetMapping(value = "/main")
	public String home(Locale locale, Model model) {
				
		return "main";
	}
	
	@GetMapping("/register")
	public String register( MemberVO member,Model model) {
						
	model.addAttribute("gradelist",memberservice.getGrade());
		return "/register";
	}
}
