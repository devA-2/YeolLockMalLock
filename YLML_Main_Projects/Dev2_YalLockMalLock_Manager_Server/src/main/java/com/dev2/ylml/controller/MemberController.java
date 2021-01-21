package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.model.service.ManagerIService;

//로그인 세션 mem
//마이페이지 접근 세션 : Allowed
@Controller
@RequestMapping("/member")
public class MemberController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ManagerIService iService;

	/**
	 * 로그인 폼 이동<br>
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request){	
		System.out.println("move to : loginForm");
		return "member/loginForm";
	}

	/**
	 * 로그인<br>
	 * 
	 * @param map
	 * @param session : 로그인세션을 가져오기 위해 사용
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, Object> map, HttpSession session) {

		//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(map.toString()); 		// 맵 정보 확인용
		MemberDto dto = iService.login(map);
		log.info("MemberController login : " + dto);
		/////////////////////////////////////
		String page ="";
		if(dto.getAuth() == 100) {
			session.setAttribute("mem", dto);
			page = "redirect:./index.do";
		}else {
			page = "redirect:./index.do";
		}
		return page;
	}	

	/**
	 * 로그인 성공여부 확인<br>
	 * 이메일 패스워드를 받아서 데이터베이스에 등록된 값인지 유효성 확인 및 맵에 성공여부를 담아서 리턴
	 * @param email
	 * @param pw
	 * @return
	 */
	@RequestMapping(value = "/loginCheckMap.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean loginCheckMap(String email, String pw) {
		Map<String,Object> iMap = new HashMap<String, Object>();
		iMap.put("email", email);
		iMap.put("pw", pw);
		MemberDto dto = iService.login(iMap);
		System.out.println("로그인 된 값: \t"+ dto);
		boolean isc;
		// TODO : boolean 이용하여 true, false로 수정할것 (해당 값은 loginForm에 있는 ajax에서 고쳐야함)
		if(dto == null) {
			isc = false;
		}else {
			isc = true;
		}
		return isc;
	}

	/**
	 * 로그아웃<br>
	 * 저장된 세션을 지워줌
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		MemberDto dto = (MemberDto)session.getAttribute("mem");
		if(dto != null) {
			//			session.removeAttribute("mem");
			session.invalidate();

		}
		return "redirect:./index.do";
	}
}
