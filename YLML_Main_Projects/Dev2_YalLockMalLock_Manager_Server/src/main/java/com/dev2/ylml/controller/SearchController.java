package com.dev2.ylml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.model.service.ManagerIService;


@Controller
public class SearchController {

	@Autowired
	private ManagerIService service;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/searchIdReport.do", method = RequestMethod.POST)
	public String searchId(String email, Model model) {
		log.info("------------------ 신고글 email 검색 실행 ------------------");
		List<ReportDto> lists = service.searchId(email);
		model.addAttribute("lists", lists);
		
		return "board/adminSearchIdReport";
	}
	
	@RequestMapping(value = "/searchIdLostProperty.do", method = RequestMethod.POST)
	public String searchId2(String receiptUserId, Model model) {
		log.info("------------------ 유실물 email 검색 실행 ------------------");
		
		List<LostPropertyDto> lists = service.searchId2(receiptUserId);
		model.addAttribute("list2", lists);
		
		return "board/searchIdLostProperty";
	}
}
