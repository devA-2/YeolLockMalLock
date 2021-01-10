package com.dev2.ylml.controller;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
		List<UserStorageListDto> list = service.selectUserStorageList(email);
		model.addAttribute("list", list);
		logger.info("userStorageList.do 실행", list);
		return "userStorageList";
	}
	
	
	
	
	
	
	
	
	
	
//	============================== 배송 ==============================
	
	@RequestMapping(value = "/deliveryForm.do", method = RequestMethod.GET)
	public String deliveryForm() {
		return "delivery/deliveryTerms";
	}
	
	@RequestMapping(value = "/searchDeliveryStation.do", method = RequestMethod.GET)
	public String searchDeliveryStation(String subway) {
		return "delivery/searchDeliveryStation";
	}
	
	@RequestMapping(value = "/checkDeliveryInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkdeliveryTime(String start, String arrive) {
		List<String> deliveryMan = service.selectDeliveryMan();
		System.out.println(deliveryMan);
		int time = 0;
		int num = 100000;
		String delManId = null;
		for (int i = 0; i < deliveryMan.size(); i++) {
			String deliverymanId = deliveryMan.get(i);
			Map<String, String> deliverManLoc = new HashMap<String, String>();
			deliverManLoc.put("deliverymanId", deliverymanId);
			deliverManLoc.put("userLoc", start);
			time = service.selectCurrnetLoc(deliverManLoc);
			if(time != -1) {
				if(time < num) {
					num = time;
				}
				if(time == num) {
					delManId = deliverymanId;
				}
			}
		}
		
		Map<String, String> stations = new HashMap<String, String>();
		stations.put("startStation", start);
		stations.put("arriveStation", arrive);
		Map<String, Integer> cost = service.selectDeliveryInfo(stations);
		Map<String, Object> info = new HashMap<String, Object>();
		if(time != -1) {
			time += cost.get("time");
			cost.put("time", time);
			info.put("cost", cost);
			info.put("delManId", delManId);
			info.put("isc", "success");
			System.out.println("CONTROLLER---"+info);
			return info;
		}else {
			info.put("isc", "false");
			return info;
		}
	}
	
	/**
	 * 
	 * @param storageId
	 * @return
	 */
	@RequestMapping(value = "/checkDeliveryGoods.do", method = RequestMethod.GET)
	public String checkDeliveryGoods(String storageId) {
		int cnt = service.selectDeliveryQty(storageId);
=======
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
>>>>>>> branch 'SONGJEONGEUN' of https://github.com/devA-2/YeolLockMalLock.git
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
