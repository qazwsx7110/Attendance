package org.zerock.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.mapper.AttendanceMapper;
import org.zerock.service.AttendanceService;
import org.zerock.service.ExcelService;
import org.zerock.vo.AttendanceDownloadVO;
import org.zerock.vo.SumWorkTimeVO;
import org.zerock.vo.VacationVO;
import org.zerock.vo.WorkTimeVO;
import org.zerock.vo.YearVacationVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/exceldownload/*")
public class ExcelController {

	@Autowired 
	private ExcelService excelservice;
	
	@Autowired
	private AttendanceService attendanceservice;
	
	@PostMapping("/attendancedownload")
    public void attendancedownload(HttpServletResponse res,String userid,String startdate,String enddate) throws Exception{

			List<AttendanceDownloadVO> attendancedownloadlist = excelservice.getExelAttendance(userid, startdate, enddate);
    	
        Workbook workbook =new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet(); // 엑셀 sheet 이름
        
        int rowCount = 0; // 데이터가 저장될 행

        Row headerRow = null;
        Cell headerCell = null;

        headerRow = sheet.createRow(rowCount++);
        
            headerCell = headerRow.createCell(0);
            headerCell.setCellValue("날짜"); // 데이터 추가
            
            headerCell = headerRow.createCell(1);
            headerCell.setCellValue("출근시간"); // 데이터 추가
            headerCell = headerRow.createCell(2);
            headerCell.setCellValue("퇴근시간"); // 데이터 추가
            headerCell = headerRow.createCell(3);
            headerCell.setCellValue("상태"); // 데이터 추가

        Row bodyRow = null;
        Cell bodyCell = null;

        for(AttendanceDownloadVO attendancedownload : attendancedownloadlist) {
        	
            bodyRow = sheet.createRow(rowCount++);
            
                bodyCell = bodyRow.createCell(0);
                bodyCell.setCellValue(attendancedownload.getAttDate()); // 데이터 추가                                      
                bodyCell = bodyRow.createCell(1);
                if(attendancedownload.getStartTime()!=null) {
                bodyCell.setCellValue(attendancedownload.getStartTime()); // 데이터 추가                               
                }
                bodyCell = bodyRow.createCell(2);
                if(attendancedownload.getEndTime()!=null) {
                bodyCell.setCellValue(attendancedownload.getEndTime());
                }// 데이터 추가                                
                bodyCell = bodyRow.createCell(3);
                bodyCell.setCellValue(attendancedownload.getState()); // 데이터 추가                          
        }

        String fileName = "spring_excel_download";

        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");       
        workbook.write(res.getOutputStream());
        workbook.close();
	}
	
	@PostMapping("/worktimedownload")
    public void worktimedownload(HttpServletResponse res,String userid,String startdate,String enddate) throws Exception{

		
			List<WorkTimeVO> worktimelist = excelservice.getExelWorkTime(userid, startdate, enddate);
			SumWorkTimeVO sumworktime = excelservice.getExelSumWorkTime(userid, startdate, enddate);
			
        Workbook workbook =new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet(); // 엑셀 sheet 이름
        
        int rowCount = 0; // 데이터가 저장될 행


        Row headerRow = null;
        Cell headerCell = null;

        headerRow = sheet.createRow(rowCount++);
        
            headerCell = headerRow.createCell(0);
            headerCell.setCellValue("일자"); // 데이터 추가
            
            headerCell = headerRow.createCell(1);
            headerCell.setCellValue("근무시간(시간)"); // 데이터 추가
            headerCell = headerRow.createCell(2);
            headerCell.setCellValue("근무시간(분)"); // 데이터 추가
            headerCell = headerRow.createCell(3);
            headerCell.setCellValue("표준시간(시간)"); // 데이터 추가
            headerCell = headerRow.createCell(4);
            headerCell.setCellValue("표준시간(분)"); // 데이터 추가
            headerCell = headerRow.createCell(5);
            headerCell.setCellValue("차이(시간)"); // 데이터 추가
            headerCell = headerRow.createCell(6);
            headerCell.setCellValue("차이(분)"); // 데이터 추가
        Row bodyRow = null;
        Cell bodyCell = null;

        for(WorkTimeVO worktime : worktimelist) {
        	System.out.println(worktime.getAttDate());
            bodyRow = sheet.createRow(rowCount++);
            
            
                bodyCell = bodyRow.createCell(0);
                bodyCell.setCellValue(worktime.getAttDate()); // 데이터 추가                    
                bodyCell = bodyRow.createCell(1);
                bodyCell.setCellValue(worktime.getWorktimehour()); // 데이터 추가    
                bodyCell = bodyRow.createCell(2);
                bodyCell.setCellValue(worktime.getWorktimeminute()); // 데이터 추가                               
                bodyCell = bodyRow.createCell(3);
                bodyCell.setCellValue(worktime.getStandardworktimehour()); // 데이터 추가                                
                bodyCell = bodyRow.createCell(4);
                bodyCell.setCellValue(worktime.getStandardworktimeminute()); // 데이터 추가  
                bodyCell = bodyRow.createCell(5);
                bodyCell.setCellValue(worktime.getIntervalhour()); // 데이터 추가   
                bodyCell = bodyRow.createCell(6);
                bodyCell.setCellValue(worktime.getIntervalminute()); // 데이터 추가   
        }
        bodyRow = sheet.createRow(rowCount++);
        bodyCell = bodyRow.createCell(0);
        bodyCell.setCellValue("총합"); // 데이터 추가                    
        bodyCell = bodyRow.createCell(1);
        bodyCell.setCellValue(sumworktime.getSumworktimehour()); // 데이터 추가    
                                   
        bodyCell = bodyRow.createCell(2);
        bodyCell.setCellValue(sumworktime.getSumworktimeminute()); // 데이터 추가                                
        
        
        bodyCell = bodyRow.createCell(5);
        bodyCell.setCellValue(sumworktime.getSumintervalhour()); // 데이터 추가   
        bodyCell = bodyRow.createCell(6);
        bodyCell.setCellValue(sumworktime.getSumintervalminute()); // 데이터 추가   
        
        

        String fileName = "spring_excel_download";

        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");       
        workbook.write(res.getOutputStream());
        workbook.close();
	}
	
	@PostMapping("/vacationdownload")
    public void vacationdownload(HttpServletResponse res,String userid,int years) throws Exception{

		Map<String, Object> vacationdownloadlist = new HashMap<String, Object>();
		
		vacationdownloadlist= attendanceservice.vacationList(years);
		
		 Workbook workbook =new SXSSFWorkbook();
	        Sheet sheet = workbook.createSheet(); // 엑셀 sheet 이름
	        
	        int rowCount = 0; // 데이터가 저장될 행
	        Row headerRow = null;
	        Cell headerCell = null;

	        headerRow = sheet.createRow(rowCount++);
	         
	        sheet.setColumnWidth(0, 6800);
	        sheet.setColumnWidth(4, 5000);
	            headerCell = headerRow.createCell(0);
	            headerCell.setCellValue("일자"); // 데이터 추가
	            headerCell = headerRow.createCell(1);
	            headerCell.setCellValue("휴가유형"); // 데이터 추가
	            headerCell = headerRow.createCell(2);
	            headerCell.setCellValue("휴가일수"); // 데이터 추가
	            headerCell = headerRow.createCell(3);
	            headerCell.setCellValue("승인일자"); // 데이터 추가
	            
	        Row bodyRow = null;
	        Cell bodyCell = null;

	        for(VacationVO vacation : (List <VacationVO>)vacationdownloadlist.get("vacationlist")) {
	        	
	            bodyRow = sheet.createRow(rowCount++);
	            
	                bodyCell = bodyRow.createCell(0);
	                bodyCell.setCellValue(vacation.getVacationdate()); // 데이터 추가                    
	                bodyCell = bodyRow.createCell(1);
	                bodyCell.setCellValue(vacation.getHoltype()); // 데이터 추가    
	                bodyCell = bodyRow.createCell(2);
	                bodyCell.setCellValue(vacation.getUsevacation()); // 데이터 추가                               
	                bodyCell = bodyRow.createCell(3);
	                bodyCell.setCellValue(vacation.getApprovaldate()); // 데이터 추가                                
	            	                
	        }
	        
	        YearVacationVO yearvacation= new YearVacationVO();
	        
	        yearvacation=(YearVacationVO)vacationdownloadlist.get("yearvacation");
	        
	        bodyRow = sheet.createRow(rowCount++);
	        bodyCell = bodyRow.createCell(0);
	        bodyCell.setCellValue("사용일수"); // 데이터 추가                    
	        bodyCell = bodyRow.createCell(1);
	        bodyCell.setCellValue(yearvacation.getUsed()); // 데이터 추가                                       
	        bodyCell = bodyRow.createCell(2);
	        bodyCell.setCellValue("남은일수"); // 데이터 추가                                                
	        bodyCell = bodyRow.createCell(3);
	        bodyCell.setCellValue(yearvacation.getRemaining()); // 데이터 추가   
	        bodyCell = bodyRow.createCell(4);
	        bodyCell.setCellValue("휴가일수"); // 데이터 추가  
	        bodyCell = bodyRow.createCell(5);
	        bodyCell.setCellValue(yearvacation.getVacation()); // 데이터 추가  
	        bodyCell = bodyRow.createCell(6);
	    
	        String fileName = "spring_excel_download";

	        res.setContentType("application/vnd.ms-excel");
	        res.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");       
	        workbook.write(res.getOutputStream());
	        workbook.close();
	}
	
}
