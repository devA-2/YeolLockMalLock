package com.dev2.ylml.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.model.service.UserIService;

@Controller
public class SearchController {

	@Autowired
	UserIService iService;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/searchIdReport.do", method = RequestMethod.POST)
	public String searchId(String email, Model model, HttpServletResponse response) throws IOException {
		log.info("------------------ 신고글 email 검색 실행 ------------------");
		List<ReportDto> lists = iService.searchId(email);
		
		if (lists.isEmpty()) { // 이부분 왜 안먹힐까 ? API서버에서 리턴받을때 실행 결과 ROW의 개수로 판단하면 되지않을까 ?
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('검색 결과가 없습니다.');</script>");
			out.flush();
			
			return "board/pagingReportList.do";
		}
		
		model.addAttribute("lists", lists);
		return "board/searchIdReport";
		
	}
	
	@RequestMapping(value = "/searchIdLostProperty.do", method = RequestMethod.POST)
	public String searchId2(String receipt_user_id, Model model) {
		log.info("------------------ 유실물 email 검색 실행 ------------------");
		List<LostPropertyDto> lists = iService.searchId2(receipt_user_id);
		model.addAttribute("list2", lists);
		
		return "board/searchIdLostProperty";
	}
}
