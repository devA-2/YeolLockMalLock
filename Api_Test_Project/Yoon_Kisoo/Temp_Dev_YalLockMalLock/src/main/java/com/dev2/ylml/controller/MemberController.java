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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.model.MemberIService;
import com.dev2.ylml.util.CoolTextService;


@Controller
public class MemberController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberIService iService;
	
	@Autowired
	private CoolTextService CoolService;
	
	
//	화면이동
	@RequestMapping(value = "/infoAgree.do", method = RequestMethod.GET)
	public String infoAgree() {
		System.out.println("move to : infoAgree");
		return "infoAgree";
	}
	
	@RequestMapping(value = "/signUpForm.do", method = RequestMethod.GET)
	public String signUpForm() {	
	System.out.println("move to : signUpForm");
		return "signUpForm";
	}
	
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request){	
	System.out.println("move to : loginForm");
		return "loginForm";
	}
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main() {	
	System.out.println("move to : loginForm");
		return "main";
	}
	@RequestMapping(value = "/myPageCheck.do", method = RequestMethod.GET)
	public String myPageCheck() {	
	System.out.println("move to : myPageCheck");
		return "myPageCheck";
	}
	
	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public String myPage() {	
	System.out.println("move to : myPage");
		return "myPage";
	}
	
	@RequestMapping(value = "/modifyInfoForm.do", method = RequestMethod.GET)
	public String modifyInfoForm() {	
	System.out.println("move to : myPage");
		return "modifyInfoForm";
	}
	
//	화면이동 
	
	@RequestMapping(value = "/insertMember.do", method = RequestMethod.POST)
	public String insertMember(MemberDto dto) {
		System.out.println(dto);
		log.info("MemberController insertMember" + dto);
		boolean isc = iService.insertMember(dto);
		return isc ? "redirect:/loginForm.do" : "redirect:/signUpForm.do";
	}
//	로그인
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(@RequestParam Map<String, Object> map, HttpSession session) {
		System.out.println(map.toString());
		MemberDto dto = iService.login(map);
		log.info("MemberController login" + dto);
		session.setAttribute("mem", dto);
		return (dto != null) ? "redirect:/index.do" : "redirect:/signUpForm.do";
	}
	
	@RequestMapping(value = "/loginCheckMap.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginCheckMap(String email, String pw) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String,Object> iMap = new HashMap<String, Object>();
		iMap.put("email", email);
		iMap.put("pw", pw);
		MemberDto dto = iService.login(iMap);
		System.out.println("로그인 된 값: \t"+ dto);

		if(dto == null) {
			map.put("isc", "실패");
		}else {
			map.put("isc","성공");
		}
		return map;
	}

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logOut(HttpSession session) {
		MemberDto dto = (MemberDto)session.getAttribute("mem");
		if(dto != null) {
			session.removeAttribute("mem");
		}
		return "redirect:/index.do";
	}
// 로그인
// Ajax 
	
	@RequestMapping(value = "/sendSMS.do",method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(String phoneNumber) {
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        log.info("수신자 번호 : " + phoneNumber);
        log.info("인증번호 : " + numStr);
        CoolService.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
    }
	
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public int idCheck(@RequestParam("mail") String email, Model model) {	
		log.info("email:" + email);
		int result = iService.idCheck(email);
		return result;

	}
	
	@RequestMapping(value = "/phoneCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public int phoneCheck(@RequestParam("phone") String phone_num, Model model) {	
		log.info("phone_num:" + phone_num);
		int result = iService.phoneCheck(phone_num);
		return result;

	}
	
}
