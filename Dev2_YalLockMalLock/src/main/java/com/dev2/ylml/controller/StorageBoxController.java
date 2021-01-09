package com.dev2.ylml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.model.service.IService;

@Controller
public class StorageBoxController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService service;
	
	@RequestMapping(value = "/storageBoxTest.do", method = RequestMethod.GET)
	public String test() {
		return "storageBoxTest";
	}
	
	/**
	 * 보관 정보 조회(사용자)
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userStorageList.do", method = RequestMethod.GET)
	public String userStorageList(String email, Model model) {
		List<UserStorageListDto> list = service.selectUserStorageList(email);
		model.addAttribute("list", list);
		logger.info("userStorageList.do 실행", list);
		return "userStorageList";
	}
	
	/**
	 * 
	 * @param storageId
	 * @return
	 */
	@RequestMapping(value = "/checkDeliveryGoods.do", method = RequestMethod.GET)
	public String checkDeliveryGoods(String storageId) {
		int cnt = service.selectDeliveryGoods(storageId);
		System.out.println("배송물량!! "+ cnt);
		return cnt<7? "success":"false";
	}

	/**
	 * 
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userDeliveryList.do", method = RequestMethod.GET)
	public String userDeliveryList(String email, Model model) {
		List<UserDeliveryListDto> list = service.selectUserDeliveryList(email);
		model.addAttribute("deliveryList", list);
		return "userDeliveryList";
	}
	
}
