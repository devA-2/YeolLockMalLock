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
import com.dev2.ylml.model.service.UserIService;
import com.dev2.ylml.naver.NaverLoginBO;
import com.dev2.ylml.util.CoolTextService;
import com.dev2.ylml.util.MailSenderHelper;
import com.github.scribejava.core.model.OAuth2AccessToken;

//로그인 세션 mem
// 마이페이지 접근 세션 : Allowed
@Controller
@RequestMapping("/member")
public class MemberController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	UserIService iService;

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
		return "member/main";

	}

	/**
	 * 개인정보보호 및 이용약관 동의 폼 이동<br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/infoAgree.do", method = RequestMethod.GET)
	public String infoAgree() {
		log.info("move to : infoAgree");
		return "member/infoAgree";
	}

	/**
	 * 회원가입 폼 이동<br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/signUpForm.do", method = RequestMethod.GET)
	public String signUpForm() {	
		System.out.println("move to : signUpForm");
		return "member/signUpForm";
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
		return "member/loginForm";
	}

	/**
	 * 아이디 찾기 폼 이동<br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/idSearchForm.do", method = RequestMethod.GET)
	public String idSearchForm() {
		System.out.println("move to : idSearchForm");
		return "member/idSearchForm";
	}

	/**
	 * 비밀번호 찾기 폼 이동<br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pwSearchForm.do", method = RequestMethod.GET)
	public String pwSearchForm() {
		log.info("memberController go to pwSearchForm");
		return "member/pwSearchForm";
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
		return "member/updateInfoForm";
	}

	/**
	 * 마이페이지 비밀번호 폼 이동<br>
	 * 비밀번호 변경
	 * @param dto
	 * @return
	 */
	@RequestMapping(value= "/updatePwForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String updatePwForm(MemberDto dto,Model model) {
		System.out.println(dto);
		model.addAttribute("email",dto.getEmail());
		log.info("memberController updatePwForm" + dto);
		return "member/updatePwForm";
	}

	/**
	 * 이메일 인증 폼 이동<br>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/emailAuthForm.do", method = RequestMethod.GET)
	public String emailAuthForm() {
		log.info("memberController go to emailAuthForm");
		return "member/emailAuthForm";
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
		return isc ? "redirect:./loginForm.do" : "redirect:./signUpForm.do";
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
	public int phoneCheck(@RequestParam("phone") String phoneNum, Model model) {	
		log.info("phoneNum:" + phoneNum);
		int result = iService.phoneCheck(phoneNum);
		return result;
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
		session.setAttribute("mem", dto);
		/////////////////////////////////////
		String page ="";
		if(dto ==null) {
			page = "redirect:./signUpForm.do";
		}else if(dto.getAuth()==19) {
			page = "redirect:./emailAuthForm.do";
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
		//DB에 저장된 email중복체크 여부가 담김 true->중복이없음

		if(emailChk) {
			log.info("추가 정보 입력폼으로");
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			return "member/extraSignUpForm";
		}else {
			//이메일이나 이름을 dto 가져오는 서비스,다오 만들어얗ㅁ 
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("email", email);
			MemberDto dto = iService.apiLogin(map);
			session.setAttribute("mem", dto);
			// 세션 스토리지 이용하여 쿠키 삭제 할 수 있음 좀더 찾아봐야ㅏㅁ
			return "member/callback";
		}
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
		return "redirect:index.do";
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
	public String idSearch(String name, String phoneNum, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("phoneNum", phoneNum);
		String email = iService.idSearch(map);
		log.info("찾은 아이디는 :"+ email);

		boolean fromIdSearch = false;
		String page = "";

		if("".equals(email) || email ==null) {
			fromIdSearch = true;
			page = "member/idSearchForm";
		}else {
			fromIdSearch = false;
			model.addAttribute("email", email);
			page = "member/idSearchResult";
		}
		System.out.println(page);
		model.addAttribute("fromIdSearch", fromIdSearch);
		return page;
	}

	/**
	 * 비밀번호 초기화
	 * 
	 * @param map
	 * @param name
	 * @param email
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pwSearch.do", method = RequestMethod.POST)
	public int pwSearch(@RequestParam Map<String, String> map, String name, String email) {
		map.put("email", email);
		map.put("name", name);
		int result = iService.pwSearch(map);
		return result;
	}

	// TODO : 리턴값 써야댐
	// 이메일 인증 전송
	@RequestMapping(value = "/sendCodeToMail.do", method = RequestMethod.GET)
	public String sendCodeToMail(String email, HttpSession session) {
		String mail = ((MemberDto) session.getAttribute("mem")).getEmail();
		log.info("세션에서 가져온 이메일 값 :" + mail);
		mailHelper.sendCode4Prove(mail);	// 메일보내기, 인증번호 기억해두는거 // java.lang.NullPointerException 클래스에서 먼가 잘못된듯?
		return "redirect:./emailAuthForm.do";
	}

	/**
	 * 메일 인증번호 체크
	 * 
	 * @param dto
	 * @param code
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/checkCode.do", method = RequestMethod.POST)
	public String checkCode(MemberDto dto, int code, HttpSession session) {
		String email = ((MemberDto) session.getAttribute("mem")).getEmail();
		if(mailHelper.checkCode4Prove(email, code) == MailSenderHelper.SUCCESS) {
			log.info("인증번호 전송 :" + dto.getEmail(),code);
			boolean emailAuth = iService.authUpdate((MemberDto)session.getAttribute("mem"));
			return "redirect:member/index.do";

		}else if (mailHelper.checkCode4Reset(email, code) == MailSenderHelper.FAILED) {
			return "redirect:member/emailAuthForm.do";
		}else {
			return "member/error";
		}
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
	 * 마이페이지 진입 세션 여부 확인<br>
	 * 세션 여부(allowed)확인하여 마이페이지 진입 혹은 다시 리다이렉트로 보냄 (하지만 한번 들어가면 세션 정보가 남아 있는거 같음 이건 따로 세션 없애는거를 구현해야 할거 같음)
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public String myPage(HttpSession session) {	
		//		System.out.println("move to : myPage -> " + (boolean)session.getAttribute("myPageAllowed"));
		boolean isc = (session.getAttribute("allowed")!= null)?(boolean)session.getAttribute("allowed"):false;
		return isc ? "member/myPage" : "redirect:./myPageCheck.do";
	}

	/**
	 * 마이페이지 진입 전 패스워드 재확인<br>
	 * 이때 개인정보 세션은 false로 초기화 해준다
	 * @return
	 */
	@RequestMapping(value = "/myPageCheck.do", method = RequestMethod.GET)
	public String myPageCheck(HttpSession session) {	
		System.out.println("move to : myPageCheck");
		session.setAttribute("allowed", false);
		return "member/myPageCheck";
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
	public Boolean pwChk(HttpSession session, String pw) {
		// loginCheckMap에서 비슷한 로직이 있어서 재활용함
		boolean result = 
				loginCheckMap(((MemberDto)session.getAttribute("mem")).getEmail(), pw);
		
		if(result) {
			session.setAttribute("allowed", true);
		}
		return result;
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
	public String updateInfo(String email, String phoneNum, HttpSession session, Model model) {
		MemberDto dto = (MemberDto) session.getAttribute("mem");
		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("email", dto.getEmail());
		infoMap.put("phoneNum", phoneNum);
		log.info(email, phoneNum);

		int result = iService.updateInfo(infoMap);
		if(result>0) {
			dto.setPhoneNum(phoneNum);
			return "redirect:./myPage.do";
		}
		return "./updateInfoForm.do";
	}

	/**
	 * 비밀번호 변경<br>
	 * 로그인 dto에서 email을 가져와서 비밀번호 변경을 해줌
	 * @param pw
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updatePw.do", method = RequestMethod.POST)
	public String updatePw(String email, String pw, HttpSession session) {
		if(session.getAttribute("mem")==null) {
			MemberDto dto = new MemberDto();
			dto.setEmail(email);
			dto.setPw(pw);
			int result = iService.updatePw(dto);
			//			System.out.println(result+"00000000000000000000000000000000000000000000");
			if(result>0) {
				return "member/loginForm";
			}
		}else {
			MemberDto dto = (MemberDto) session.getAttribute("mem");
			dto.setPw(pw);
			int result = iService.updatePw(dto);
			if(result>0) {
				return "redirect:./myPage.do";
			}
		}
		return "./updatePwForm.do";
	}

	/**
	 * 탈퇴전 사용중인 서비스 확인
	 * @param email
	 * @return
	 */
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
		return "redirect:./logout.do";
	}
}
