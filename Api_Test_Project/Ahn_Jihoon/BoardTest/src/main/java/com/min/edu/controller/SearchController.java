package com.min.edu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.edu.dto.ReportDto;
import com.min.edu.model.ISearchService;

@Controller
public class SearchController {

	@Autowired
	private ISearchService service;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/searchId.do", method = RequestMethod.POST)
	public String searchId(String email, Model model) {
		log.info("------------------ email 검색 실행 ------------------");
		List<ReportDto> lists = service.searchId(email);
		model.addAttribute("list", lists);
		
		return "searchId";
	}
	
}
