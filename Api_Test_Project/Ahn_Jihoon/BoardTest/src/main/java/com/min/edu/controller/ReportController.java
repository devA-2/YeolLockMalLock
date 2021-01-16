package com.min.edu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.edu.dto.LostPropertyDto;
import com.min.edu.dto.MemberDto;
import com.min.edu.dto.ReportDto;
import com.min.edu.model.ILostPropertyService;
import com.min.edu.model.IReportService;
import com.min.edu.model.TestILoginService;
import com.min.edu.util.MailService;

@Controller
public class ReportController {

	@Autowired
	private IReportService service; // 신고글 Service
	
	@Autowired
	private TestILoginService service2; // 로그인 Service
	
	@Autowired
	private ILostPropertyService service3; // 유실물 Service
	
	@Autowired
	private MailService mailService;
	
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
	
	// login session들고다니면서 테스트 진행함에 따라 사용하지 않는 상태. 취합할때 이걸로 변경해야함.
	@RequestMapping(value="/reportList.do", method=RequestMethod.GET)
	public String reportList(HttpSession session, Model model) {
		
		log.info("------------------ 신고글 목록 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem");
		session.setAttribute("mem", mem);
		List<ReportDto> list = service.selectAllReport();
		model.addAttribute("lists", list);

		return "reportList";
	}
	
//	수정 예정
	@RequestMapping(value="/selectDetailReport.do", method=RequestMethod.GET)
	public String selectOneReport(String refer, Model model, HttpSession session) {
		log.info("------------------ 상세글 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem"); //
		List<ReportDto> dto = service.selectDetailReport(refer);
		model.addAttribute("dto", dto);
		session.setAttribute("mem", mem);
		return "selectDetailReport";
	}
	
	
	@RequestMapping(value = "/replyReport.do", method=RequestMethod.GET)
	public String replyReport(String seq, HttpSession session, Model model) {
		log.info("------------------ 답변 글 작성 페이지 이동 ------------------");
		ReportDto rDto = service.selectDetail(seq);
		MemberDto mDto = (MemberDto)session.getAttribute("mem");
		
		model.addAttribute("dto", rDto);
		session.setAttribute("mem", mDto);
		
		return "reply";
	}
	
	@RequestMapping(value = "/replyDo.do", method=RequestMethod.GET)
	public String reply(ReportDto dto, HttpSession session, Model model) {
		log.info("------------------ 답변 글 작성 후 신고 글 목록으로 이동 ------------------");		
		String email = service.selectDetail(dto.getSeq()).getEmail();
		String title = dto.getTitle();
		String content = dto.getContent();
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+content);
		
		// Map으로 이메일에 들어갈 정보 보낼때 사용할수있음
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("email", email);
//		map.put("title", email);
//		map.put("content", email);
		
		service.reply(dto);

		mailService.sendMail(email, title, content); // 여기가 널포인트가 뜬다 ?
		return "redirect:/reportList.do";
	}
	
	@RequestMapping(value = "/selectDetail.do", method=RequestMethod.GET)
	public String selectDetail(String seq, HttpSession session, Model model) {
		log.info("------------------ 답글 입력에서 상세글로 뒤로가기 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem");
		ReportDto dto = service.selectDetail(seq);
		
		session.setAttribute("dto", dto);
		session.setAttribute("mem", mem);
		
		
//		model.addAttribute("dto", dto);
		return "selectDetailReport";
	}
	
	

	// 사용 고민
//	@RequestMapping(value = "/modifyReport.do", method=RequestMethod.GET)
//	public String modifyReport(String seq, Model model) {
//		log.info("------------------ 글 수정으로 이동 ------------------");
//		List<ReportDto> dto = service.selectOneReport(seq);
//		model.addAttribute("dto", dto);
//		
//		return "modifyReport";
//	}
	
	// 사용 고민
//	@RequestMapping(value = "/modify.do", method = RequestMethod.GET)
//	public String modify(ReportDto dto) {
//		log.info("------------------ 글 수정후 상세 글로 이동 ------------------");
//		boolean isc = service.modifyReport(dto);
//		
//		if (isc == true) {
//			return "redirect:/selectDetailReport.do?refer="+dto.getRefer();
//		}else {
//			return "redirect:/reportList.do";
//		}
//	}
	
	@RequestMapping(value = "/insertReport.do", method = RequestMethod.GET)
	public String insertReport(String seq, HttpSession session, ReportDto dto) {
		log.info("------------------ 신고 글 작성 ------------------");
		MemberDto mDto = (MemberDto)session.getAttribute("mem");
		session.setAttribute("mem", mDto);
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
