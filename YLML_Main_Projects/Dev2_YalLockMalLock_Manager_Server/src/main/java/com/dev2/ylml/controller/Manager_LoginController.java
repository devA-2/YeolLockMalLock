package com.dev2.ylml.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.Manager_MemberDto;
import com.dev2.ylml.model.service.ManagerIService;

@Controller
public class Manager_LoginController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ManagerIService service;
	
	// index에서 관리자로그인페이지 이동 managerLogin
	@RequestMapping(value = "movetoLogin.do", method = RequestMethod.GET)
	public String managerLogin() {
		logger.info("managerLogin.do : 관리자로그인페이지 이동");
		
		return "managerLoginMain";
	}
	
	// 로그인 성공
		@RequestMapping(value = "/login.do", method = RequestMethod.GET)
		public String login(@RequestParam Map<String, Object> map, HttpSession session) {
			// 로그인기능 해결되면 주석 풀어줄 것
//			Manager_MemberDto mDto = service.loginMember(map);
			
			//---------------- 테스트중 -------------------------
			Map<String, String> mDto = new HashMap<String, String>();
			mDto.put("email", "admin@naver.com");
			mDto.put("pw", "$2a$10$BRDwr8nIkOmkwapVdXKuweLbiafw/Jyj7KSPUod3zIYvSJqRysegu");
			mDto.put("auth", "100");
			System.out.println(mDto);
			//----------------------------------------------------
			
			session.setAttribute("mangerLogin", mDto);
			return "redirect:managerMain.do";
		}
	
	
	// 로그인체크
		@RequestMapping(value = "/loginCheckMap.do", method = RequestMethod.POST)
		@ResponseBody
		public Map<String, String> loginCheckMap(String email, String pw){
			Map<String, String> map = new HashMap<String, String>();
			Map<String, Object> iMap = new HashMap<String, Object>();
			iMap.put("email", email);
			iMap.put("pw", pw);
			
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ : "+pw);
			
			Manager_MemberDto mDto = service.loginMember(iMap);
			System.out.println("로그인 된 값 : \t "+ mDto);
			if(mDto == null) {
				map.put("isc", "실패");
			}else {
				map.put("isc", "성공");
			}
			return map;
		}
	
	
	
	
}
