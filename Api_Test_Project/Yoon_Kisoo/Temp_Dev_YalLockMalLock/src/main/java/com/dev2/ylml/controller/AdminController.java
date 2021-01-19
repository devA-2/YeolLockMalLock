package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.UserGoodsDto;
import com.dev2.ylml.model.service.MemberIService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {
	
	@Autowired
	private MemberIService service;

	@RequestMapping(value = "/allMember.do",method = RequestMethod.GET)
	public String selectAll(Model model,@RequestParam(required=false) String email) {
		List<MemberDto> list;
		if(email==null || email.isBlank()) {
			list = service.selectAll(new String());
		}else {
			list = service.selectAll(email);
		}
		log.info(list+"");
		model.addAttribute("list",list);
		return "allMember";
	}
	
	@RequestMapping(value = "/memberDetail.do",method = RequestMethod.GET)
	public String memberDetail(String email,Model model) {
		MemberDto mDto = service.detailMember(email);
		List<UserGoodsDto> usingList = service.memberUsing(email);
		
		model.addAttribute("mDto",mDto);
		model.addAttribute("usingList",usingList);
		return "memberDetail";
	}
	
	@ResponseBody
	@RequestMapping(value = "/memberIdSearch.do")
	public List<String> memberIdSearch(){
		return service.memberIdSearch();
	}
	

	
}
