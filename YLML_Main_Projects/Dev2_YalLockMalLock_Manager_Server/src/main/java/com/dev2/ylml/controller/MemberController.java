package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.List;
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
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.model.service.ManagerIService;
import com.dev2.ylml.util.PagingVO;

//로그인 세션 mem
//마이페이지 접근 세션 : Allowed
@Controller
public class MemberController {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ManagerIService iService;

	@RequestMapping(value = "index.do")
	public String index() {
		return "index";
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
		MemberDto dto = iService.adminLogin(map);
		log.info("MemberController login : " + dto);
		session.setAttribute("mem", dto);
		/////////////////////////////////////
		String page ="";
		if(dto==null) {
			page = "redirect:index.do";
			
		}else if(dto.getAuth() == 99) {
			page = "redirect:index.do";

		}else{
			page = "redirect:managerMain.do";
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
		log.info("로그인 입력 값 -> email :"+ email );
		log.info("로그인 입력 값 -> password :"+ pw );
		
		Map<String,Object> iMap = new HashMap<String, Object>();
		iMap.put("email", email);
		iMap.put("pw", pw);
		MemberDto dto = iService.adminLogin(iMap);
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
			session.invalidate();

		}
		return "redirect:index.do";
	}
	
	/**
	 * 회원 전체리스트
	 * @param model
	 * @param email
	 * @return
	 */
//	@RequestMapping(value = "/allMember.do",method = RequestMethod.GET)
//	public String selectAll(Model model,@RequestParam(required=false) String email) {
//		List<MemberDto> list;
//		if(email==null || email.isBlank()) {
//			list = iService.selectAll(new String());
//		}else {
//			list = iService.selectAll(email);
//		}
//		log.info(list+"");
//		model.addAttribute("list",list);
//		return "allMember";
//	}
	
	@RequestMapping(value="/allMember.do")
	public String pagingReportList(PagingVO vo, Model model
			//email이 null일때 오류 -> 디폴트를 @로 
			, @RequestParam(value="email",required=false,defaultValue = "@") String email
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) {

		log.info("회원 조회 ");
		int total = iService.countMember(email);
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
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("start",vo.getStart());
		map.put("end", vo.getEnd());
		model.addAttribute("viewAll", iService.selectAll(map));
		model.addAttribute("email",email);
		return "allMember";
	}
	/**
	 * 회원 상세정보
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/memberDetail.do",method = RequestMethod.GET)
	public String memberDetail(String email,Model model) {
		MemberDto dto = iService.detailMember(email);
		List<StorageGoodsDto> usingList = iService.memberUsing(email);
		
		model.addAttribute("dto",dto);
		model.addAttribute("usingList",usingList);
		return "memberDetail";
	}
	
	/**
	 * 회원 아이디로 검색
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/memberIdSearch.do")
	public List<String> memberIdSearch(){
		return iService.memberIdSearch();
	}
	
}
