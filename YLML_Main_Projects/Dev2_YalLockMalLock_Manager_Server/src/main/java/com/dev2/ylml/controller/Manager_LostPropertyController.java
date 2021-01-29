package com.dev2.ylml.controller;

import java.util.List;

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

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.model.service.ManagerIService;

@Controller
public class Manager_LostPropertyController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ManagerIService service;
	
	@RequestMapping(value = "/lostPropertyList.do", method=RequestMethod.GET)
	public String lostPropertyList(Model model) {
		log.info("------------------ 유실물 목록 ------------------");
		List<LostPropertyDto> lists = service.selectAllLostProperty();
		model.addAttribute("lists", lists);
		return "board/lostPropertyList";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/jqGridLostProperty.do")
	@ResponseBody
	public JSONArray jqGridLostProperty() {
		log.info("------------------ jqGridLostProperty 값 뿌려주기 ------------------");
		
		List<LostPropertyDto> list = service.selectAllLostProperty();
		
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		
		for (LostPropertyDto dto : list) {
			jsonObject = new JSONObject();
			jsonObject.put("seq", dto.getSeq());
			jsonObject.put("receiptUserId", dto.getReceiptUserId());
			jsonObject.put("lostRegdate", dto.getLostRegdate());
			jsonObject.put("andDate", dto.getAndDate());
			jsonArray.add(jsonObject);
		}
		
		
		return jsonArray;
	}
	
	@RequestMapping(value="/selectOneLostProperty.do", method=RequestMethod.GET)
	public String selectOneLostProperty(String seq, Model model, HttpSession session) {
		log.info("------------------ admin 상세글 ------------------");
		MemberDto mem = (MemberDto)session.getAttribute("mem"); //
		LostPropertyDto dto = service.selectOneLostProperty(seq);
		
		model.addAttribute("dto", dto);
		session.setAttribute("mem", mem);
		
		return "board/selectOneLostProperty";
	}
	
	@RequestMapping(value="/testMidnight.do", method=RequestMethod.GET)
	public String testMidnight() {
		System.out.println("test1");
		int result = service.scheduledForMidnight();
		System.out.println("test2");
		return "board/scheduleTest";
	}
	
}
