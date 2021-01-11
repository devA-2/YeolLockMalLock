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

// 로그인 세션 : mem
// 마이페이지 접근 세션 : myPageAllowed
@Controller
public class MemberController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberIService iService;
	
	@Autowired
	private CoolTextService CoolService;
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/infoAgree.do", method = RequestMethod.GET)
	public String infoAgree() {
		System.out.println("move to : infoAgree");
		return "infoAgree";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/signUpForm.do", method = RequestMethod.GET)
	public String signUpForm() {	
		System.out.println("move to : signUpForm");
		return "signUpForm";
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request){	
		System.out.println("move to : loginForm");
		return "loginForm";
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/myPageCheck.do", method = RequestMethod.GET)
	public String myPageCheck(HttpSession session) {	
		System.out.println("move to : myPageCheck");
		session.setAttribute("allowed", false);
		return "myPageCheck";
	}
	
	@RequestMapping(value = "/idSearchForm.do", method = RequestMethod.GET)
	public String idSearchForm() {
		System.out.println("move to : idSearchForm");
		return "idSearchForm";
	}

//	/**
//	 * 마이페이지 정보 수정<br>
//	 * 휴대폰번호 수정
//	 * @param dto
//	 * @return
//	 */
	@RequestMapping(value= "/updateInfoForm.do", method = RequestMethod.GET)
	public String updatePw(MemberDto dto) {
		System.out.println(dto);
		log.info("memberController updateInfoForm" + dto);
		return "updateInfoForm";
	}
	
	/**
	 * 마이페이지 비밀번호 변경<br>
	 * 비밀번호 변경
	 * @param dto
	 * @return
	 */
	@RequestMapping(value= "/updatePwForm.do", method = RequestMethod.GET)
	public String updatePwForm(MemberDto dto) {
		System.out.println(dto);
		log.info("memberController updatePwForm" + dto);
		return "updatePwForm";
	}
	/**
	 * 마이페이지 진입 세션 여부 확인<br>
	 * 세션 여부(myPageAllowed)확인하여 마이페이지 진입 혹은 다시 리다이렉트로 보냄 (하지만 한번 들어가면 세션 정보가 남아 있는거 같음 이건 따로 세션 없애는거를 구현해야 할거 같음)
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public String myPage(HttpSession session) {	
//		System.out.println("move to : myPage -> " + (boolean)session.getAttribute("myPageAllowed"));
		boolean isc = (session.getAttribute("allowed")!= null)?(boolean)session.getAttribute("allowed"):false;
		return isc ? "myPage" : "redirect:myPageCheck.do";
	}
	
	/**
	 * 회원가입<br>
	 * 아이디, 패스워드, 이름, 휴대폰 입력받아 회원가입
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/insertMember.do", method = RequestMethod.POST)
	public String insertMember(MemberDto dto) {
		System.out.println(dto);
		log.info("MemberController insertMember" + dto);
		boolean isc = iService.insertMember(dto);
		return isc ? "redirect:/loginForm.do" : "redirect:/signUpForm.do";
	}
	
	@RequestMapping(value = "/updateInfo.do", method = RequestMethod.POST)
	public String updateInfo(String email, String phone_num, HttpSession session, Model model) {
		String mail = (String)session.getAttribute(email);
		String phone = (String)session.getAttribute(phone_num);
		
		
		return "myPage.do";
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
		System.out.println(map.toString());
		MemberDto dto = iService.login(map);
		log.info("MemberController login" + dto);
		session.setAttribute("mem", dto);
		return (dto != null) ? "redirect:/index.do" : "redirect:/signUpForm.do";
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
	public Map<String, String> loginCheckMap(String email, String pw) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String,Object> iMap = new HashMap<String, Object>();
		iMap.put("email", email);
		iMap.put("pw", pw);
		MemberDto dto = iService.login(iMap);
		System.out.println("로그인 된 값: \t"+ dto);
		// TODO : boolean 이용하여 true, false로 수정할것 (해당 값은 loginForm에 있는 ajax에서 고쳐야함)
		if(dto == null) {
			map.put("isc", "실패");
		}else {
			map.put("isc","성공");
		}
		return map;
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
			session.removeAttribute("mem");
		}
		return "redirect:/index.do";
	}
	
	@RequestMapping(value = "/idSearch.do", method = RequestMethod.POST)
	public String idSearch(String Email, String phone_num) {
		log.info("MemberController findId" + "찾은 아이디 ", Email);
		return "index.do";
	}
	
// Ajax 
	
	//TODO : 인증번호를 세션에 태워서 회원가입 확인 버튼을 누르더라도 서버단에서 체크할 것 (로직수정 필요)
	/**
	 * SMS인증 폰 번호를 입력받아 난수를 생성해서, 인증번호로 전송<br>
	 * @param phoneNumber
	 * @return
	 */
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
	
	/**
	 * 아이디 중복검사<br>
	 * db에서 email를 카운트해서 0이 나오면 사용가능한 아이디라고 출력
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/idCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public int idCheck(@RequestParam("mail") String email, Model model) {	
		log.info("email:" + email);
		int result = iService.idCheck(email);
		return result;
	}
	
	/**
	 * 휴대폰 중복검사 <br>
	 * @param phone_num
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/phoneCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public int phoneCheck(@RequestParam("phone") String phone_num, Model model) {	
		log.info("phone_num:" + phone_num);
		int result = iService.phoneCheck(phone_num);
		return result;
	}
	
	/**
	 * 마이페이지 진입전 패스워드 체크<br>
	 * 
	 * @param session
	 * @param pw
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pwChk.do", method = RequestMethod.POST)
	public Map<String, String> pwChk(HttpSession session, String pw) {
		// loginCheckMap에서 비슷한 로직이 있어서 재활용함
		Map<String, String> result = loginCheckMap(((MemberDto)session.getAttribute("mem")).getEmail(), pw);
		
		if(result.get("isc").equals("성공")) {
			session.setAttribute("allowed", true);
		}
		return result;
	}
	
}
