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
import com.dev2.ylml.model.service.UserIService;

@Controller
public class LostPropertyContoller {

	@Autowired
	UserIService iService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/lostPropertyList.do", method=RequestMethod.GET)
	public String lostPropertyList(Model model) {
		log.info("------------------ 유실물 목록 ------------------");
		List<LostPropertyDto> lists = iService.selectAllLostProperty();
		model.addAttribute("lists", lists);
		return "board/lostPropertyList";
	}
	
	@RequestMapping(value = "selectOneLostProperty.do", method=RequestMethod.GET)
	public String selectOneLostProperty(String seq, Model model) {
		log.info("------------------ 유실물 상세 목록 ------------------");
		LostPropertyDto dto = iService.selectOneLostProperty(seq);
		model.addAttribute("dto", dto);
		return "board/selectOneLostProperty";
	}
	
}
