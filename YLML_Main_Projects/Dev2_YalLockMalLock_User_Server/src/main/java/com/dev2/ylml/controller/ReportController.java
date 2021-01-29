package com.dev2.ylml.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.model.service.UserIService;
import com.dev2.ylml.util.PagingVO;

@Controller
public class ReportController {

	@Autowired
	UserIService iService;
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/goMainPage.do", method=RequestMethod.GET)
	public String goMainPage() {
		log.info("------------------ 메인으로 이동 ------------------");
		return "index";
	}
	
	@RequestMapping(value="/goReportList.do", method=RequestMethod.GET)
	public String goReportList() {
		log.info("------------------ 신고글 목록 ------------------");
		return "reportList";
	}
	
	@RequestMapping(value="/pagingReportList.do")
	public String pagingReportList(PagingVO vo, Model model,HttpSession session
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		log.info("------------------ 신고글 목록 ------------------");
		
		int total = iService.countReport();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", vo);
		model.addAttribute("viewAll", iService.selectReport(vo));
		return "board/pagingReportList";
	}
	
	
	// login session들고다니면서 테스트 진행함에 따라 사용하지 않는 상태. 취합할때 이걸로 변경해야함.
	@RequestMapping(value="/reportList.do", method=RequestMethod.GET)
	public String reportList(HttpSession session, Model model) {
		
		log.info("------------------ 신고글 목록 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem");
		session.setAttribute("mem", mem);
		
		List<ReportDto> list = iService.selectAllReport();
		model.addAttribute("lists", list);

		return "board/reportList";
	}
	
	
	@RequestMapping(value = "selectOneReportAjax.do", method=RequestMethod.GET)
	public String reportListAjax(HttpSession session, Model model, String seq) {
		ReportDto dto = iService.selectDetail(seq);
		MemberDto mem = (MemberDto)session.getAttribute("mem");
		model.addAttribute("dto", dto);
		session.setAttribute("mem", mem);
		
		return "selectDetailReport";
//		return "adminReportList";
	}
	
//	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/reportListAjax.do", method = RequestMethod.GET)
	public String reportListAjax(Model model, HttpSession session) {
		
		List<ReportDto> lists = iService.selectAllReport();
		MemberDto mem = (MemberDto)session.getAttribute("mem");
		model.addAttribute("lists", lists);
		session.setAttribute("mem", mem);
		
//		List<ReportDto> list = service.selectAllReport();
//		JSONArray jsonArray = null;
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			String jsonArrayStr = mapper.writeValueAsString(list);
//			System.out.println(jsonArrayStr);
//			JSONParser parser = new JSONParser();
//			jsonArray = (JSONArray) parser.parse(jsonArrayStr);
//			model.addAttribute("model", jsonArray);
//		} catch (JsonProcessingException e) {
//			// TODO error page 처리
//			e.printStackTrace();
//		}
// catch (ParseException e) {
//			// TODO error page 처리
//			e.printStackTrace();
//		}
		
		
//		for (ReportDto dto : list) {
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
		
//			 ObjectMapper mapper = new ObjectMapper();
//			 String jsonStr = null;
//			try {
//				jsonStr = mapper.writeValueAsString(dto);
//				System.out.println(jsonStr);
//				 JSONParser parser = new JSONParser();
//				 jsonObject = (JSONObject)parser.parse(jsonStr);
//			} catch (JsonProcessingException e) {
//				e.printStackTrace();
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}

//			jsonArray.add(jsonObject);
		return "board/viewReportList";
	}
	
//	수정 예정
	@RequestMapping(value="/selectDetailReport.do", method=RequestMethod.GET)
	public String selectOneReport(String refer, Model model, HttpSession session) {
		log.info("------------------ 상세글 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem"); //
		
		List<ReportDto> lists = iService.selectDetailReport(refer);
		
		model.addAttribute("lists", lists);
		session.setAttribute("mem", mem);
		return "board/selectDetailReport";
	}
	
	
	@RequestMapping(value = "/replyReport.do", method=RequestMethod.GET)
	public String replyReport(String seq, HttpSession session, Model model) {
		log.info("------------------ 답변 글 작성 페이지 이동 ------------------");
		ReportDto rDto = iService.selectDetail(seq);
		MemberDto mDto = (MemberDto)session.getAttribute("mem");
		
		model.addAttribute("dto", rDto);
		session.setAttribute("mem", mDto);
		
		return "board/reply";
	}
	
	@RequestMapping(value = "/replyDo.do", method=RequestMethod.GET)
	public String reply(ReportDto dto, HttpSession session, Model model) {
		log.info("------------------ 답변 글 작성 후 신고 글 목록으로 이동 ------------------");		
//		String email = iService.selectDetail(dto.getSeq()).getEmail();
//		String title = dto.getTitle();
//		String content = dto.getContent();
		
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+content);
		
		// Map으로 이메일에 들어갈 정보 보낼때 사용할수있음
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("email", email);
//		map.put("title", email);
//		map.put("content", email);
		
		iService.reply(dto);

//		MailService.sendMail(email, title, content);  // 메일서비스 이거 세개 파라미터 받는거 새로 만들어야함
		
		return "redirect:/adminReportList.do";
	}
	
	@RequestMapping(value = "/selectDetail.do", method=RequestMethod.GET)
	public String selectDetail(String seq, HttpSession session, Model model) {
		log.info("------------------ 답글 입력에서 상세글로 뒤로가기 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem");
		ReportDto dto = iService.selectDetail(seq);
		
		session.setAttribute("dto", dto);
		session.setAttribute("mem", mem);
		
		
//		model.addAttribute("dto", dto);
		return "board/selectDetailReport";
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
		return "board/insertReport";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String insert(ReportDto dto, HttpSession session, String ir1) {
		log.info("------------------ 신고 글 작성 후 신고 글 목록으로 이동 ------------------");
		MemberDto mDto = (MemberDto)session.getAttribute("mem");
		dto.setEmail(mDto.getEmail());
		
		dto.setContent(ir1);
		
		boolean isc = iService.insertReport(dto);
		
		return isc?"redirect:reportList.do":"redirect:insertReport.do";
	}
	
	// ---------------------------------- 테스트용 샘플 ----------------------------------
	
//	@RequestMapping(value = "/backPage.do", method=RequestMethod.POST)
//	public String backPage(HttpSession session, @RequestParam Map<String, Object> map) {
//		MemberDto mDto = service2.loginMember(map);
//		session.setAttribute("mem", mDto); // session에서 로그인 정보 가지고 reportList로 보내는 용도.
//		return "reportList";
//	}
//	
//
//	// 신고글게시판 테스트용 로그인
//	@RequestMapping(value = "/adminLogin.do", method=RequestMethod.POST)
//	public String adminLogin(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
//		MemberDto mDto = iService.loginMember(map);
//		session.setAttribute("mem", mDto);
//		
//		if(mDto == null) {
//			return "error";
//		}
//		
//
////		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+list);
//		
//		return "adminReportList";
//	}
//	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/jqGrid.do")
//	@ResponseBody
//	public JSONArray jqGrid() {
//		
//		List<ReportDto> list = iService.selectAllReport();
//		
//		JSONArray jsonArray = new JSONArray();
//		JSONObject jsonObject = null;
//		
//		for (ReportDto dto : list) {
//			jsonObject = new JSONObject();
//			jsonObject.put("email", dto.getEmail());
//			jsonObject.put("seq", dto.getSeq());
//			jsonObject.put("title", dto.getTitle());
//			jsonObject.put("regdate", dto.getRegdate());
//			jsonArray.add(jsonObject);
//		}
//		
//		
//		return jsonArray;
//	}
//	
//	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
//	public String login(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
//		MemberDto mDto = iService.loginMember(map);
//		session.setAttribute("mem", mDto);
//		if(mDto == null) {
//			return "error";
//		}
//		List<ReportDto> list = iService.selectAllReport();
//		model.addAttribute("lists", list);
//		
//		return "reportList";
//	}
//	
//	// 유실물 테스트용 로그인
//	@RequestMapping(value = "/login2.do", method = RequestMethod.POST)
//	public String login2(@RequestParam Map<String, Object> map, HttpSession session, Model model) {
//		MemberDto mDto = iService.loginMember(map);
//		System.out.println("mDto : "+mDto);
//		session.setAttribute("mem", mDto);
//		List<LostPropertyDto> list = iService.selectAllLostProperty();
//		model.addAttribute("lists", list);
//		
//		if(mDto == null) {
//			return "error";
//		}
//		
//		
//		return "lostPropertyList";
//		
//	}
//
//	
//	// Admin 신고글 게시판 jqGrid 적용 Ajax 처리 테스트
//	@RequestMapping(value="/adminReportList.do", method=RequestMethod.GET)
//	public String adminReportList(HttpSession session, Model model) {
//		
//		log.info("------------------ 신고글 목록 ------------------");
//		MemberDto mem = (MemberDto)session.getAttribute("mem");
//		session.setAttribute("mem", mem);
//		List<ReportDto> list = iService.selectAllReport();
//		model.addAttribute("lists", list);
//		
//		
//		return "adminReportList";
//	}
	

}
