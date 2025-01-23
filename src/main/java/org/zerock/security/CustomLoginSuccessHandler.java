package org.zerock.security;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.vo.MemberVO;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.warn("Login Success");
		Principal principal;
		
		List<String> roleNames = new ArrayList<>();
		
		authentication.getAuthorities().forEach(authority->{
			
			roleNames.add(authority.getAuthority());
			
			
		});
		
		log.warn("ROLE NAMES:"+roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			
			response.sendRedirect("/main");
			return;
		}
		
		
        if(roleNames.contains("ROLE_MEMBER")) {
			
			response.sendRedirect("/main");
			return;
		}

        
        
        response.sendRedirect("/");
	}

}
