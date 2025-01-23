package org.zerock.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	public static boolean onmaintainance = false;
	// 매일 7시에 시작
		@Scheduled(cron = "0 0 7 * * *")
		  public void maintainanceStart() throws Exception {	  
			  onmaintainance = true;
			  System.out.println("점검시작");
		  }
		
		// 매일 8시에 시작
		@Scheduled(cron = "0 0 8 * * *")
			  public void maintainanceEnd() throws Exception {	  
				  onmaintainance = false;
				  System.out.println("점검끝");
			  }
}
