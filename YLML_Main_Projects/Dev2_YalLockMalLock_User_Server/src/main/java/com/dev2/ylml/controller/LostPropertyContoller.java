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

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.model.service.UserIService;
import com.dev2.ylml.util.PagingVO;

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
		
		System.out.println("----------------------------------------------------------"+lists);
		return "board/lostPropertyList";
	}
	
	@RequestMapping(value = "/selectOneLostProperty.do", method=RequestMethod.GET)
	public String selectOneLostProperty(String seq, Model model) {
		log.info("------------------ 유실물 상세 목록 ------------------");
		LostPropertyDto dto = iService.selectOneLostProperty(seq);
		model.addAttribute("dto", dto);
		return "board/selectOneLostProperty";
	}
	
	@RequestMapping(value="/pagingLostPropertyList.do")
	public String pagingReportList(PagingVO vo, Model model,HttpSession session
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		log.info("------------------ 유실물 목록 ------------------");
		
		int total = iService.countLostProperty();
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
		model.addAttribute("viewAll", iService.selectAllPagingLostProperty(vo));
		return "board/pagingLostPropertyList";
	}
	
}
