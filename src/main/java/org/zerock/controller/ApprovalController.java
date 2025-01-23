package org.zerock.controller;

import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Base64.Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.security.CustomUser;
import org.zerock.service.ApprovalService;
import org.zerock.service.MemberService;
import org.zerock.vo.ApprovalPageVO;
import org.zerock.vo.ApprovalVO;
import org.zerock.vo.CompleteApprovalPageVO;
import org.zerock.vo.MemberPageVO;
import org.zerock.vo.MemberVO;
import org.zerock.vo.PageDTO;
import org.zerock.vo.RejectApprovalPageVO;
import org.zerock.vo.ViewApprovalVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/approval/*")
public class ApprovalController {

	@Autowired 
	private ApprovalService approvalservice;
	
	@GetMapping("/registerapproval")
	public String registerApproval(Model model) {
		
		 CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		
		model.addAttribute("ApprovalMemberList",approvalservice.getApprovalMemberList(user.getMember()));
		
		System.out.print(user.getMember().getCode());
		System.out.print(user.getMember().getGrade());
		System.out.print(approvalservice.getApprovalMemberList(user.getMember()));
		
		return "/approval/approval";
	}
	
	@PostMapping("/addline")
	@ResponseBody
	public List<MemberVO> addline(@RequestParam("line") String userid) {
		System.out.print("결제라인추가");		
		System.out.print(userid);
		
		System.out.print(approvalservice.getgrade(userid));
		
		 CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
			 
		 MemberVO member= new MemberVO();
		 member.setCode(user.getMember().getCode());
		 member.setGrade(approvalservice.getgrade(userid));			
		 
		 List<MemberVO> list=approvalservice.getApprovalMemberList(member);
		 
		 System.out.print(list);
		 
		return list;
	}
	
	@PostMapping("/submitapproval")
	public String registerapproval(@ModelAttribute ApprovalVO approval,@RequestParam("approvalhierarchy") String[] approvalhierarchy) {
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		approval.setCode(user.getMember().getCode());
		//approval.setApprovalNumber("");
		approval.setWriter(user.getMember().getUserId());
		approval.setWriterName(user.getMember().getUserName());
		approval.setWriterGrade(user.getMember().getGrade());
		approvalservice.insertapproval(approval,approvalhierarchy,user.getMember().getCode());
		 System.out.println(approval.getTitle());
		 for (int i = 0; i < approvalhierarchy.length; i++) {
			    System.out.println(approvalhierarchy[i]);
			}
		return "/approval/waitapproval";
	}
	
	@GetMapping("/waitapproval")
	public String waitApproval(Model model) {
		System.out.println("대기서류");
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ApprovalPageVO page= new ApprovalPageVO(user.getMember().getUserId());
		
		int waitappovallisttotalcount= approvalservice.getWaitApprovalListTotalCount(page);
	    
		model.addAttribute("waitApprovalList", approvalservice.getWaitApprovalList(page));
		model.addAttribute("pageMaker", new PageDTO(page,waitappovallisttotalcount));
		
		return "/approval/waitapproval";
	}
	

	@GetMapping("/waitapprovalload")
	public String waitApprovalLoad(@ModelAttribute ApprovalPageVO page,Model model) {		
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		page.setApprover(user.getMember().getUserId());
		
		int waitappovallisttotalcount= approvalservice.getWaitApprovalListTotalCount(page);
		
		model.addAttribute("waitApprovalList", approvalservice.getWaitApprovalList(page));
		model.addAttribute("pageMaker", new PageDTO(page,waitappovallisttotalcount));
			
		return "/approval/waitapproval";
	}
	
	
	@GetMapping("/viewapproval")
	public String viewApproval(@RequestParam("ano") Long approvalnumber,Model model) {
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(approvalnumber);	
		List<ViewApprovalVO> approvallist= approvalservice.getViewApproval(user.getMember().getCode(), approvalnumber);
		
		
		model.addAttribute("viewapproval", approvallist.get(0));
		model.addAttribute("sign", approvallist);
		return "/approval/viewapproval";
	}
	
	@PostMapping("/approval")
	public void Approval(String code,int approvalnumber,int signnumber,String sign,String complete,String vacation,String startdate,String enddate,String writer){
		System.out.println(complete);
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String uploadFolder="C:\\upload\\approval\\"+user.getMember().getCode();
		String uploadpath = "";
		UUID uuid = UUID.randomUUID();
		String[] strParts=sign.split(",");

		String rstStrImg=strParts[1];  

		String filenm=uuid+"";

		BufferedImage image=null;

		byte[] byteImg;
		Decoder decode = Base64.getDecoder();

		byteImg = decode.decode(rstStrImg);  //base64 디코더를 이용하여 byte 코드로 변환

		ByteArrayInputStream bis= new ByteArrayInputStream(byteImg);

		try {
			image= ImageIO.read(bis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   //BufferedImage형식으로 변환후 저장

		try {
			bis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		uploadpath=uploadFolder+"\\"+filenm+".png";

		File folderObj= new File(uploadFolder);

		if( !folderObj.isDirectory() ) {
			folderObj.mkdir();
		}

		File outputFile= new File(uploadpath);  //파일객체 생성

		try {
			ImageIO.write(image, "png", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //서버에 파일로 저장
		
		approvalservice.approval(filenm,signnumber,code,approvalnumber,complete,vacation,startdate,enddate,writer);
		
		System.out.println("이미지 등록");
		
	}
	
	@PostMapping("/reject")
	public ResponseEntity<String> Reject(String code,int approvalnumber) {
		System.out.println("반려");
		int result= approvalservice.reject(code, approvalnumber);
		
		return result ==1
				? new ResponseEntity<>("success",HttpStatus.OK)
			    :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/completeapproval")
	public String completeApproval(Model model) {
		System.out.println("완료서류");
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		CompleteApprovalPageVO page= new CompleteApprovalPageVO(user.getMember().getUserId());
		
		int completeappovallisttotalcount= approvalservice.getCompleteApprovalListTotalCount(page);
	    
		model.addAttribute("completeApprovalList", approvalservice.getCompleteApprovalList(page));
		model.addAttribute("pageMaker", new PageDTO(page,completeappovallisttotalcount));
		
		return "/approval/completeapproval";
	}
	
	@GetMapping("/completeapprovalload")
	public String completeApprovalLoad(@ModelAttribute CompleteApprovalPageVO page,Model model) {		
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		page.setWriter(user.getMember().getUserId());
		
		int completeappovallisttotalcount= approvalservice.getCompleteApprovalListTotalCount(page);
		
		model.addAttribute("completeApprovalList", approvalservice.getCompleteApprovalList(page));
		model.addAttribute("pageMaker", new PageDTO(page,completeappovallisttotalcount));
			
		return "/approval/completeapproval";
	}
	
	@GetMapping("/rejectapproval")
	public String rejectApproval(Model model) {
		System.out.println("반려서류");
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		RejectApprovalPageVO page= new RejectApprovalPageVO(user.getMember().getUserId());
		
		int rejectappovallisttotalcount= approvalservice.getRejectApprovalListTotalCount(page);
	    
		model.addAttribute("rejectApprovalList", approvalservice.getRejectApprovalList(page));
		model.addAttribute("pageMaker", new PageDTO(page,rejectappovallisttotalcount));
		
		return "/approval/rejectapproval";
	}
	
	@GetMapping("/rejectapprovalload")
	public String rejectApprovalLoad(@ModelAttribute RejectApprovalPageVO page,Model model) {
		System.out.println("반려서류");
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		page.setWriter(user.getMember().getUserId());
		
		int rejectappovallisttotalcount= approvalservice.getRejectApprovalListTotalCount(page);
	    
		model.addAttribute("rejectApprovalList", approvalservice.getRejectApprovalList(page));
		model.addAttribute("pageMaker", new PageDTO(page,rejectappovallisttotalcount));
		
		return "/approval/rejectapproval";
	}
	
	@GetMapping("/viewsign")
	@ResponseBody
	public ResponseEntity<byte[]> getSign(String signname) {
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		System.out.println(signname);
		
		File file = new File("C:\\upload\\approval\\"+user.getMember().getCode()+"\\"+signname+".png");
		
		ResponseEntity<byte[]> result = null;
		
		try {
			HttpHeaders header =new HttpHeaders();
			header.add("Content-Type","image/png");
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);
		}catch(IOException e) {
			e.printStackTrace();
		}
				return result;		
	}
	
	@GetMapping("/viewcompleteapproval")
	public String viewCompleteApproval(@RequestParam("ano") Long approvalnumber,Model model) {
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(approvalnumber);	
		List<ViewApprovalVO> approvallist= approvalservice.getViewApproval(user.getMember().getCode(), approvalnumber);
		
		
		model.addAttribute("viewapproval", approvallist.get(0));
		model.addAttribute("sign", approvallist);
		return "/approval/viewcompleteapproval";
	}
	
	@GetMapping("/viewrejectapproval")
	public String viewRejectApproval(@RequestParam("ano") Long approvalnumber,Model model) {
		
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(approvalnumber);	
		List<ViewApprovalVO> approvallist= approvalservice.getViewApproval(user.getMember().getCode(), approvalnumber);
		
		
		model.addAttribute("viewapproval", approvallist.get(0));
		model.addAttribute("sign", approvallist);
		return "/approval/viewrejectapproval";
	}
}
