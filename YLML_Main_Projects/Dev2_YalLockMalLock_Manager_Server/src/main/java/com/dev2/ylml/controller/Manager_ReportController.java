package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.model.service.ManagerIService;
import com.dev2.ylml.util.MailService;

@Controller
public class Manager_ReportController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ManagerIService service;
	
	@Autowired
	private MailService mailService;
	
	// Admin 신고글 게시판 jqGrid 적용 Ajax 처리 테스트
	@RequestMapping(value="/adminReportList.do", method=RequestMethod.GET)
	public String adminReportList(HttpSession session, Model model) {
		
		log.info("------------------ 신고글 목록 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem");
		session.setAttribute("mem", mem);
		List<ReportDto> list = service.selectAllReport();
		model.addAttribute("lists", list);
		
//	ArrayList<ReportDto> list = (ArrayList<ReportDto>)(service.selectAllReport());
//	JsonObject jsonObejct = new JsonObject();
//	
//	JsonArray cell = new JsonArray();
//	ReportDto data = null;
//	
//	for (int i = 0; i < list.size(); i++) {
//		data = (ReportDto)list.get(i);
//
//		JsonObject obj = new JsonObject();
//		
//		obj.add("seq",data.getSeq());
//		
//		
//	}
		
		return "board/adminReportList";
	}

	
	@RequestMapping(value="/adminSelectDetailReport.do", method=RequestMethod.GET)
	public String adminSelectOneReport(String refer, Model model, HttpSession session) {
		log.info("------------------ admin 상세글 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem"); //
		List<ReportDto> dto = service.selectDetailReport(refer);
		model.addAttribute("dto", dto);
		session.setAttribute("mem", mem);
		return "board/adminSelectDetailReport";
	}
	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value="/selectDetailReportAjax.do", method=RequestMethod.GET)
//	public JSONArray selectDetailReportAjax(String refer, Model model, HttpSession session) {
//		log.info("------------------ 상세글 ------------------");
//		
//		MemberDto mem = (MemberDto)session.getAttribute("mem"); 
//		List<ReportDto> lists = service.selectDetailReport(refer);
//		
//		model.addAttribute("dto", lists);
//		session.setAttribute("mem", mem);
//		
//		JSONArray jsonArray = new JSONArray();
//		JSONObject jsonObject = null;
//		
//		for (ReportDto dto : lists) {
//		jsonObject = new JSONObject();
//		jsonObject.put("seq", dto.getSeq());
//		jsonObject.put("email", dto.getEmail());
//		jsonObject.put("title", dto.getTitle());
//		jsonObject.put("content", dto.getContent());
//		jsonObject.put("regdate", dto.getRegdate());
//		jsonObject.put("category", dto.getCategory());
//		jsonObject.put("image", dto.getImage());
//		jsonObject.put("delflag", dto.getDelflag());
//		jsonObject.put("process_status", dto.getProcess_status());
//		jsonObject.put("refer", dto.getRefer());
//		jsonObject.put("step", dto.getStep());
//		jsonArray.add(jsonObject);
//		}
//		
//		return jsonArray;
//	}
	
	@RequestMapping(value = "/replyReport.do", method=RequestMethod.GET)
	public String replyReport(String seq, HttpSession session, Model model) {
		log.info("------------------ 답변 글 작성 페이지 이동 ------------------");
		ReportDto rDto = service.selectDetailGoReply(seq);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 동작 되는지 테스해본다 ~");
		MemberDto mDto = (MemberDto)session.getAttribute("mem");
		
		model.addAttribute("dto", rDto); // 답변 달려고상세글에 대한 dto정보
		session.setAttribute("mem", mDto);
		
		return "board/reply";
	}
	
	@RequestMapping(value = "/replyDo.do", method=RequestMethod.GET)
	public String reply(ReportDto dto, HttpSession session, Model model) {
		log.info("------------------ 답변 글 작성 후 신고 글 목록으로 이동 ------------------");		
		String email = service.selectDetail(dto.getSeq()).getEmail();
		String title = dto.getTitle();
		String content = dto.getContent();
		service.reply(dto);
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+content);
		
		// Map으로 이메일에 들어갈 정보 보낼때 사용할수있음
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("title", email);
		map.put("content", email);
		

		mailService.sendMail(email, title, content); 
		
		return "redirect:board/adminReportList.do";
	}
	
	@RequestMapping(value = "/selectDetail.do", method=RequestMethod.GET)
	public String selectDetail(String seq, HttpSession session, Model model) {
		log.info("------------------ 답글 입력에서 상세글로 뒤로가기 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem");
		ReportDto dto = service.selectDetailGoReply(seq);
		
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
	
	
//	@RequestMapping(value = "/backPage.do", method=RequestMethod.POST)
//	public String backPage(HttpSession session, @RequestParam Map<String, Object> map) {
//		MemberDto mDto = service2.loginMember(map);
//		session.setAttribute("mem", mDto); // session에서 로그인 정보 가지고 reportList로 보내는 용도.
//		return "reportList";
//	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/jqGrid.do")
	@ResponseBody
	public JSONArray jqGrid() {
		
		List<ReportDto> list = service.selectAllReport();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		for (ReportDto dto : list) {
			jsonObject = new JSONObject();
			jsonObject.put("email", dto.getEmail());
			jsonObject.put("seq", dto.getSeq());
			jsonObject.put("title", dto.getTitle());
			jsonObject.put("regdate", dto.getRegdate());
			jsonObject.put("refer", dto.getRefer());
			jsonArray.add(jsonObject);
		}
		
		
		return jsonArray;
	}
	
	
	
}
