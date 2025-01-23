package org.zerock.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@EnableScheduling
@Controller
@Log4j
public class CommonController {
	
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth,Model model) {
		
		log.info("access Denied"+"auth");
		
		model.addAttribute("msg","Access Denied");
	}
	
	@GetMapping("/")
	public String loginInput(String error,String logout,Model model) {
		
		log.info("error"+error);
		log.info("logout"+logout);
		
		if(error !=null) {
			
			model.addAttribute("error","로그인실패");
			return "home";
		}
		
        if(logout !=null) {
			
			model.addAttribute("logout","로그아웃");
			return "home";
		}
        
        return "home";
	}
	
	@GetMapping("/customLogout")
	public void logoutGET() {
		
		log.info("custom logout");
	}
}
