package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxListDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.model.service.IService;

@Controller
public class StorageBoxController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService service;
	
	/**
	 * 보관 정보 조회(사용자)
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userStorageList.do", method = RequestMethod.GET)
	public String userStorageList(String email, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		List<UserStorageListDto> storageList = service.selectUserStorageList(map);
		model.addAttribute("list", storageList);
		logger.info("Controller_userStorageList.do 실행");
		return "list/userStorageList";
	}
	
	
	
	
	
	
	
	
	
	
//	============================== 배송 ==============================
	
	@RequestMapping(value = "/idSeq.do", method = RequestMethod.POST)
	@ResponseBody
	public String idSeq(@RequestBody String param, HttpSession session) {
		logger.info("Controller_idSeq.do 실행");
		return "success";
	}
	
	@RequestMapping(value = "/deliveryForm.do", method = RequestMethod.GET)
	public String deliveryForm() {
		logger.info("Controller_deliveryForm.do 실행");
		return "delivery/deliveryTerms";
	}
	
	@RequestMapping(value = "/searchDeliveryStation.do", method = RequestMethod.GET)
	public String searchDeliveryStation(Model model, HttpSession session) {
		model.addAttribute("storageId", session.getAttribute("storageId"));
		System.out.println("확인확인!!!!! "+model);
		logger.info("Controller_searchDeliveryStation.do 실행");
		return "delivery/searchDeliveryStation";
	}
	
	/**
	 * 배송 거리/비용 조회
	 * @param start
	 * @param arrive
	 * @return
	 */
	@RequestMapping(value = "/checkDeliveryInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkdeliveryTime(String start, String arrive) {
		List<StorageBoxListDto> sbListDto = service.selectStorageBoxList(start);
		String startStation= sbListDto.get(0).getSubway();
		System.out.println("확인!!!!!!!!!!"+startStation);
		
		// 전체 배송원 조회
		List<MemberDto> deliveryMan = service.selectDeliveryMan();
		int time = 0;
		int num = 100000;
		Map<String, String> delManInfo = new HashMap<String, String>();
		
		// 현재 보관함에서 가까운 배송원 탐색
		for (int i = 0; i < deliveryMan.size(); i++) {
			String deliverymanId = deliveryMan.get(i).getEmail();
			String deliverymanName = deliveryMan.get(i).getName();
			Map<String, String> deliverManLoc = new HashMap<String, String>();
			deliverManLoc.put("deliverymanId", deliverymanId);
			deliverManLoc.put("userLoc", startStation);
			time = service.selectCurrnetLoc(deliverManLoc);
			if(time != -1) {
				if(time < num) {
					num = time;
				}
				if(time == num) {
					delManInfo.put("delManId", deliverymanId);
					delManInfo.put("delManName", deliverymanName);
				}
			}
		}
		
		// 배송 시간/비용 조회
		Map<String, String> stations = new HashMap<String, String>();
		stations.put("startStation", startStation);
		stations.put("arriveStation", arrive);
		Map<String, Integer> cost = service.selectDeliveryInfo(stations);
		
		// 화면으로 전달할 값 저장
		Map<String, Object> info = new HashMap<String, Object>();
		if(time != -1) {
			time += cost.get("time");
			cost.put("time", time);
			info.put("cost", cost);
			info.put("delManInfo", delManInfo);
			info.put("isc", "success");
			logger.info("Controller_checkDeliveryInfo.do 실행");
			return info;
		}else {
			info.put("isc", "false");
			logger.info("Controller_checkDeliveryInfo.do 실행");
			return info;
		}
	}
	
	@RequestMapping(value = "/delivery.do", method = RequestMethod.POST)
	public String delivery(DeliveryDto delDto, StorageGoodsDto goodsDto) {
		System.out.println("@@@@@@DeliveryDto@@@@@"+delDto);
		System.out.println("@@@@@@StorageGoodsDto@@@@@"+goodsDto);
//		boolean isc = service.insertDelivery(delDto, goodsDto);
		logger.info("Controller_delivery.do 실행");
		return "delivery/deliverySuccess";
//		return isc?"redirect:/deliverySuccess.do":"redirect:/userStorageList.do";
	}

	/**
	 * 배송 조회(사용자)
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userDeliveryList.do", method = RequestMethod.GET)
	public String userDeliveryList(String email, Model model) {
		List<UserDeliveryListDto> list = service.selectUserDeliveryList(email);
		model.addAttribute("deliveryList", list);
		logger.info("Controller_checkDeliveryInfo.do 실행");
		return "list/userDeliveryList";
	}
	
}
