package com.min.edu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.dto.LostPropertyDto;
import com.min.edu.dto.MemberDto;
import com.min.edu.dto.ReportDto;
import com.min.edu.model.ILostPropertyService;
import com.min.edu.model.IReportService;
import com.min.edu.model.TestILoginService;

@Controller
public class ReportController {

	@Autowired
	private IReportService service; // 신고글 Service
	
	@Autowired
	private TestILoginService service2; // 로그인 Service
	
	@Autowired
	private ILostPropertyService service3; // 유실물 Service
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/index.do")
	public String index() {
		log.info("------------------ index page ------------------");
		return "index";
	}
	
	@RequestMapping(value="/goReportList.do", method=RequestMethod.GET)
	public String goReportList() {
		log.info("------------------ 신고글 목록 ------------------");
		return "reportList";
	}
	
	@RequestMapping(value="/reportList.do", method=RequestMethod.GET)
	public String reportList(Model model) {
		log.info("------------------ 신고글 목록 ------------------");
		List<ReportDto> list = service.selectAllReport();
		model.addAttribute("lists", list);
		return "reportList";
	}
	
//	수정 예정
	@RequestMapping(value="/selectDetailReport.do", method=RequestMethod.GET)
	public String selectOneReport(String refer, Model model, HttpSession session) {
		log.info("------------------ 상세글 ------------------");
		
		
//		MemberDto mDto = (MemberDto)session.getAttribute("mem"); //
		List<ReportDto> dto = service.selectDetailReport(refer);
		System.out.println("ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ" + dto + "ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ");
		model.addAttribute("dto", dto);
		return "selectDetailReport";
	}
	
//	@RequestMapping(value = "/modifyReport.do", method=RequestMethod.GET)
//	public String modifyReport(String seq, Model model) {
//		log.info("------------------ 글 수정으로 이동 ------------------");
//		List<ReportDto> dto = service.selectOneReport(seq);
//		model.addAttribute("dto", dto);
//		
//		return "modifyReport";
//	}
	
	@RequestMapping(value = "/modify.do", method = RequestMethod.GET)
	public String modify(ReportDto dto) {
		log.info("------------------ 글 수정후 상세 글로 이동 ------------------");
		boolean isc = service.modifyReport(dto);
		
		if (isc == true) {
			return "redirect:/selectDetailReport.do?refer="+dto.getRefer();
		}else {
			return "redirect:/reportList.do";
		}
	}
	
	@RequestMapping(value = "/insertReport.do", method = RequestMethod.GET)
	public String insertReport(String seq, HttpSession session, ReportDto dto) {
		log.info("------------------ 신고 글 작성 ------------------");
		MemberDto mDto = (MemberDto)session.getAttribute("mem");
		return "insertReport";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insert(ReportDto dto, HttpSession session) {
		log.info("------------------ 신고 글 작성 후 신고 글 목록으로 이동 ------------------");
		MemberDto mDto = (MemberDto)session.getAttribute("mem");
		dto.setEmail(mDto.getEmail());
		service.insertReport(dto);
		
		return "redirect:/reportList.do";
	}
	
	public String replyReport() {
		
		return "";
	}
	
//	@RequestMapping(value = "/backPage.do", method=RequestMethod.POST)
//	public String backPage(HttpSession session, @RequestParam Map<String, Object> map) {
//		MemberDto mDto = service2.loginMember(map);
//		session.setAttribute("mem", mDto); // session에서 로그인 정보 가지고 reportList로 보내는 용도.
//		return "reportList";
//	}
	
	
	// 신고글게시판 테스트용 로그인
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		MemberDto mDto = service2.loginMember(map);
		System.out.println("mDto : "+mDto);
		session.setAttribute("mem", mDto);
		if(mDto == null) {
			return "error";
		}
		List<ReportDto> list = service.selectAllReport();
		model.addAttribute("lists", list);
		
		return "reportList";
	}
	
	// 유실물 테스트용 로그인
	@RequestMapping(value = "/login2.do", method = RequestMethod.POST)
	public String login2(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
		MemberDto mDto = service2.loginMember(map);
		System.out.println("mDto : "+mDto);
		session.setAttribute("mem", mDto);
		List<LostPropertyDto> list = service3.selectAllLostProperty();
		model.addAttribute("lists", list);
		
		if(mDto == null) {
			return "error";
		}
		
		
		return "lostPropertyList";
		
	}


}
