package com.dev2.ylml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev2.ylml.dto.Manager_MemberDto;
import com.dev2.ylml.model.service.ManagerIService;

@Controller
public class Manager_DeliveryMemberController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ManagerIService service;

	@RequestMapping(value = "index.do")
	public String index() {
		return "index";
	}

	/**
	 * 관리자 메인으로 managerMain.do
	 * 
	 * @return managerMain.jsp 이동
	 */
	@RequestMapping(value = "managerMain.do")
	public String managerMain() {
		logger.info("managerMain.do : 메인페이지로 이동");
		return "managerMain";
	}

	/**
	 * 담당자 및 배송원의 전체 정보조회 allDeleveryList.do 전체정보 "list"에 담아서 model로
	 * DeliverymemberList.jsp에 전송
	 * 
	 * @param model
	 * @return DeliverymemberList.jsp 이동
	 */
	@RequestMapping(value = "allDeleveryList.do", method = RequestMethod.GET)
	public String allDeleveryList(Model model) {
		logger.info("allDeleveryList.do : 담당자 및 배송원 전체 정보조회 이동");

		return "deliverymemberList";
	}

	/**
	 * 상세조회 + 배송정보 deliveryDetail.do 상세조회 "list1", 배송정보 "list2"에 담아서 model로
	 * deliveryDetail.jsp에 전송
	 * 
	 * @param model
	 * @param email
	 * @return deliveryDetail.jsp 이동
	 */
	@RequestMapping(value = "deliveryDetail.do", method = RequestMethod.GET)
	public String deliveryDetail(Model model, @RequestParam String email) {
		logger.info("deliveryDetail.do : 상세조회 이동 : " + email);

		Manager_MemberDto list1 = service.selectDetail(email);
		Manager_MemberDto list2 = service.DeliveryInfo(email);
		model.addAttribute("list1", list1);
		model.addAttribute("list2", list2);

		logger.info("deliveryDetail.do : 상세조회 -> list1 : " + list1);
		logger.info("deliveryDetail.do : 배송정보 -> list2 : " + list2);
		return "deliveryDetail";
	}

	

	/** 전체조회에서 id로 담당자 및 배송원 조회 검색 결과를 list로 model객체에 담아서
	 * viewList로 전달
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "list.do", method = RequestMethod.GET)
	public String list(Model model, String param) {
		logger.info("list.do 검색결과 : " + param);
		
		if (param == null) {
			List<Manager_MemberDto> lists = service.selectAllDelivery();
			model.addAttribute("lists", lists);
	      }
			else {
			Manager_MemberDto list = service.selectIdDelivery(param);
	         model.addAttribute("list", list);
	      }
		return "deliveryViewList";
	}

	
	/** 전체조회에서 임시권한을 가진 배송원 및 담당자를 조회
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewTempAuth.do", method = RequestMethod.GET)
	public String viewTempAuth(Model model) {
		logger.info("viewTempAuth 임시권한 보기 : " );
		
		List<Manager_MemberDto> TempList = service.selectTempDelivery();
		model.addAttribute("TempList", TempList);
		
		return "deliveryViewList";
	}

	
	
	// modifyAuth 임시권한 변경
	/** 임시권한을 가진 배송원 및 담당자의 권한을 정식권한으로 변경
	 * @param model
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "modifyAuth.do", method = RequestMethod.GET)
	public String modifyAuth(Model model,@RequestParam String email) {
		logger.info("modifyAuth.do 임시권한 변경 : " + email );
		
		boolean isc = service.modifyAuth(email);
//		String ischeck = (String.valueOf(isc));
//		model.addAttribute("ischeck", ischeck);
		
		logger.info("권한변경 결과 : " + isc );
//		logger.info("권한변경 결과 : " + ischeck );
		return "redirect:deliveryDetail.do?email="+email;
	}
	

}
