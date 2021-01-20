package com.dev2.ylml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.model.service.DeliveryIService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/storage")
public class StorageController {

	@Autowired
	private DeliveryIService service;
	
	/**
	 * 배송 메인 > 배송 조회(사용자)
	 * 배송 메인 > 배송 조회(배송원)
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deliveryList.do", method = RequestMethod.GET)
	public String userDeliveryList(String email, String auth, Model model) {
		List<DeliveryDto> deliveryList = service.selectDeliveryList(email, auth);
		model.addAttribute("deliveryList", deliveryList);
		model.addAttribute("auth", auth);
		System.out.println("DTO 확인!!"+deliveryList);
		log.info("Controller_checkDeliveryInfo.do 실행");
		return "deliveryList";
	}
	
	/**
	 * 배송 물품 수령 버튼 클릭(배송원) > 배송 시작 시간 업데이트
	 * @param deliveryCode
	 * @return
	 */
	@RequestMapping(value = "/receipt.do", method = RequestMethod.POST)
	@ResponseBody
	public String receipt(@RequestParam("deliveryCode") String deliveryCode) {
		service.updatedeliveryStrat(deliveryCode);
		log.info("Controller_pickUp.do 실행");
		return "success";
	}
	
}
