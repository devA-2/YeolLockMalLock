package com.dev2.ylml.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import com.dev2.ylml.model.service.MemberIService;
import com.dev2.ylml.naver.NaverLoginBO;
import com.dev2.ylml.util.CoolTextService;
import com.dev2.ylml.util.MailSenderHelper;
import com.github.scribejava.core.model.OAuth2AccessToken;

// 로그인 세션 : mem
// 마이페이지 접근 세션 : Allowed
@Controller
public class MemberController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemberIService iService;
	
	@Autowired
	private CoolTextService CoolService;
	
	private NaverLoginBO naverLoginBO;
	
	private MailSenderHelper mailHelper;
	
	@Autowired
	private void setMailSenderHelper(MailSenderHelper mailHelper) {
		this.mailHelper = mailHelper;
	}
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	/***
	 *  메인으로 이동
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String main(Model model, HttpSession session) {
		log.info("move to : main");
		String naverUrl = naverLoginBO.getAuthorizationUrl(session);
		model.addAttribute("naverUrl", naverUrl);
		return "main";
		
	}
	
	/**
	 * 개인정보보호 및 이용약관 동의 폼 이동<br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/infoAgree.do", method = RequestMethod.GET)
	public String infoAgree() {
		log.info("move to : infoAgree");
		return "infoAgree";
	}
	
	/**
	 * 회원가입 폼 이동<br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/signUpForm.do", method = RequestMethod.GET)
	public String signUpForm() {	
		System.out.println("move to : signUpForm");
		return "signUpForm";
	}
	
	/**
	 * 로그인 폼 이동<br>
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
	 * 마이페이지 진입 전 패스워드 재확인<br>
	 * 이때 개인정보 세션은 false로 초기화 해준다
	 * @return
	 */
	@RequestMapping(value = "/myPageCheck.do", method = RequestMethod.GET)
	public String myPageCheck(HttpSession session) {	
		System.out.println("move to : myPageCheck");
//		boolean isc = (session.getAttribute("allowed")!=null):?(boolean)session.getAttribute("api"):false;
//		session.setAttribute("api", false);
		session.setAttribute("allowed", false);
		return "myPageCheck";
	}
	
	/**
	 * 아이디 찾기 폼 이동<br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/idSearchForm.do", method = RequestMethod.GET)
	public String idSearchForm() {
		System.out.println("move to : idSearchForm");
		return "idSearchForm";
	}
	
	/**
	 * 마이페이지 진입 세션 여부 확인<br>
	 * 세션 여부(allowed)확인하여 마이페이지 진입 혹은 다시 리다이렉트로 보냄 (하지만 한번 들어가면 세션 정보가 남아 있는거 같음 이건 따로 세션 없애는거를 구현해야 할거 같음)
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
	 * 마이페이지 정보 폼 이동<br>
	 * 휴대폰번호 수정
	 * @param dto
	 * @return
	 */
	@RequestMapping(value= "/updateInfoForm.do", method = RequestMethod.GET)
	public String updatePw(MemberDto dto) {
		System.out.println(dto);
		log.info("memberController updateInfoForm" + dto);
		return "updateInfoForm";
	}
	
	/**
	 * 마이페이지 비밀번호 폼 이동<br>
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
	@RequestMapping(value = "/emailAuthForm.do", method = RequestMethod.GET)
	public String emailAuthForm() {
		log.info("memberController go to emailAuthForm");
		return "emailAuthForm";
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
	
	/**
	 * 개인정보 수정<br>
	 * @param email
	 * @param phone_num
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateInfo.do", method = RequestMethod.POST)
	public String updateInfo(String email, String phone_num, HttpSession session, Model model) {
		MemberDto dto = (MemberDto) session.getAttribute("mem");
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("email", dto.getEmail());
		infoMap.put("phone_num", phone_num);
		log.info(email, phone_num);

		int result = iService.updateInfo(infoMap);
		if(result>0) {
			dto.setPhone_num(phone_num);
			return "redirect:myPage.do";
		}
		return "updateInfoForm.do";
	}
	
	// TODO : 암호화 해야함...
	/**
	 * 비밀번호 변경<br>
	 * @param email
	 * @param phone_num
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updatePw.do", method = RequestMethod.POST)
	public String updatePw(String email, String pw, HttpSession session) {
		MemberDto dto = (MemberDto) session.getAttribute("mem");
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("email", dto.getEmail());
		infoMap.put("pw", pw);
		log.info(email, pw);

		int result = iService.updateInfo(infoMap);
		if(result>0) {
			dto.setPw(pw);
			return "redirect:myPage.do";
		}
		return "updatePwForm.do";
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
		System.out.println(map.toString()); 		// 맵 정보 확인용
		MemberDto dto = iService.login(map);
		log.info("MemberController login" + dto);
		session.setAttribute("mem", dto);
		/////////////////////////////////////
		if(dto.getAuth() != 10) {
			return "redirect:/emailAuthForm.do"; // 그냥 로그인 버튼을 눌러서 19인 경우에는 emailAuthForm으로 보내는게 낫나? 마이페이지 체크처럼?
		}
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
//			session.removeAttribute("mem");
			session.invalidate();
			
		}
		return "redirect:/index.do";
	}
	
	/**
	 * 아이디 찾기 <br>
	 * dto의 name과 phone_num을 map에 담아 검색 그 후 dto에 담아서 email을 불러옴<br
	 * 화면단에서 출력해주어야함....
	 * @param name
	 * @param phone_num
	 * @return
	 */
	@RequestMapping(value = "/idSearch.do", method = RequestMethod.POST)
	public String idSearch(String name, String phone_num, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("phone_num", phone_num);
		String email = iService.idSearch(map);
		log.info("찾은 아이디는 :"+ email);
		
		boolean fromIdSearch = false;
		String page = "";
		
		if("".equals(email) || email ==null) {
			fromIdSearch = true;
			page = "idSearchForm";
		}else {
			fromIdSearch = false;
			model.addAttribute("email", email);
			page = "idSearchResult";
		}
		System.out.println(page);
		model.addAttribute("fromIdSearch", fromIdSearch);
		return page;
	}
	
	// TODO : 리턴값 써야댐
	// 이메일 인증 전송
	@RequestMapping(value = "/sendCodeToMail.do", method = RequestMethod.GET)
	public String sendCodeToMail(String email, HttpSession session) {
		String mail = ((MemberDto) session.getAttribute("mem")).getEmail();
		log.info("세션에서 가져온 이메일 값 :" + mail);
		mailHelper.sendCode4Prove(mail);	// 메일보내기, 인증번호 기억해두는거 // java.lang.NullPointerException 클래스에서 먼가 잘못된듯?
		return "redirect:emailAuthForm.do";
	}
	
	//이메일 인증번호 체크
	@RequestMapping(value = "/checkCode.do", method = RequestMethod.POST)
	public String checkCode(MemberDto dto, int code, HttpSession session) {
		String email = ((MemberDto) session.getAttribute("mem")).getEmail();
		if(mailHelper.checkCode4Prove(email, code) == MailSenderHelper.SUCCESS) {
			log.info("인증번호 전송 :" + dto.getEmail(),code);
			boolean emailAuth = iService.authUpdate((MemberDto)session.getAttribute("mem"));
					return "redirect:index.do";
					
		}else if (mailHelper.checkCode4Reset(email, code) == MailSenderHelper.FAILED) {
			return "redirect:emailAuthForm.do";
		}else {
			return "error";
		}
			
		
			
	}
	
	@RequestMapping(value = "/usingCheck.do", method = RequestMethod.POST)
	@ResponseBody
	public int usingCheck(@RequestParam("mail") String email) {
		int result = iService.usingCheck(email);
		return result;
	}
	
	/**
	 *
	 * 회원탈퇴<br>
	 * 로그인 세션에서 이메일을 불러와서 델 플레그 처리
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/quitMember.do", method = RequestMethod.GET)
	public String quitMember(HttpSession session) {
		String email = ((MemberDto)session.getAttribute("mem")).getEmail();
		int result = iService.quitMember(email);
		// TODO : 탈퇴가 정상적으로 처리되었다고 띄어주어야함 어디서 띄우지..?
		return "redirect:logout.do";
	}
	
	
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
	
	// TODO: 토큰 삭제 및 토큰 연장 등등... 구현해야함...
	/**
	 * 네이버 간편 로그인 <br>
	 *
	 * 네이버로 부터 access 토큰을 통해 정보를 가져와서 아이디 중복 체크를 통해 없다면 extraSignUp폼으로 보내고 있다면 로그인 되고 인덱스 페이지로 보냄
	 * @param model
	 * @param code
	 * @param state
	 * @param session
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/naverCallback.do", method = RequestMethod.GET)
	public String naverCallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		String apiResult = naverLoginBO.getUserProfile(oauthToken);
		
		// String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject)obj;

		// 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		// response의 nickname값 파싱
		String name = (String)response_obj.get("name");
		String email = (String)response_obj.get("email");
		
		boolean emailChk = (iService.idCheck(email)==0);
		
		if(emailChk) {
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			return "extraSignUpForm";
		}else {
			MemberDto dto = new MemberDto();
			dto.setEmail(email);
			dto.setName(name);
			dto.getAuth();
			dto.getPhone_num();
			dto.getReg_date();
			
			session.setAttribute("mem", dto);
//			session.getAttribute("api");
			// 세션 스토리지 이용하여 쿠키 삭제 할 수 있음 좀더 찾아봐야ㅏㅁ
			return "callback";
		}
	}
	
}
