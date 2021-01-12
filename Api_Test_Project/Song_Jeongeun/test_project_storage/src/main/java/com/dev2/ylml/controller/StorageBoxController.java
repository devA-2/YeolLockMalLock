package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String idSeq(@RequestParam("storageId") String storageId ,@RequestParam("boxSeq") int boxSeq, HttpSession session) {
		session.setAttribute("storageId", storageId);
		session.setAttribute("boxSeq", boxSeq);
		logger.info("Controller_idSeq.do 실행 : {}",storageId,boxSeq);
		return "success";
	}
	
	@RequestMapping(value = "/deliveryForm.do", method = RequestMethod.GET)
	public String deliveryForm() {
		logger.info("Controller_deliveryForm.do 실행");
		return "delivery/deliveryTerms";
	}
	
	@RequestMapping(value = "/searchDeliveryStation.do", method = RequestMethod.GET)
	public String searchDeliveryStation() {
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
	public Map<String, Object> checkdeliveryTime(@RequestParam("arriveStation") String arriveStation, HttpSession session) {
		// 사용자 현재 보관함 위치
		String userStorageId = (String) session.getAttribute("storageId");
		List<StorageBoxListDto> userSBListDto = service.selectStorageBoxList(userStorageId);
		String userStorageSubway = userSBListDto.get(0).getSubway();
		
		// 배송역 정보
		List<StorageBoxListDto> deliverySBListDto = service.selectStorageBoxList(arriveStation);
		String deliveryStorageSubway = deliverySBListDto.get(0).getSubway();
		
		// 타임테이블 seq
		int userLocSeq = (int) session.getAttribute("boxSeq");
		int deliveryLocSeq = service.selectTimeTableSeq(deliveryStorageSubway);
		
		// 1. 배송원 현재 위치 > 사용자 보관함 거리 계산
		//  1) 전체 배송원 조회
		List<MemberDto> deliveryMans = service.selectDeliveryMan();
		//  2) 사용자 보관함과 가까운 배송원 탐색
		int deliverymanCnt = 0;
		int num = 1000;
		Map<String, Object> delManInfo = new HashMap<String, Object>();
		for (int i = 0; i < deliveryMans.size(); i++) {
			//  2-1) 배송원 ID, 이름 탐색
			String deliverymanId = deliveryMans.get(i).getEmail();
			String deliverymanName = deliveryMans.get(i).getName();
			// 2-2) 배송원 위치 탐색
			String deliverymanSubway = service.selectDeliveryLoc(deliverymanId);
			int deliverymanLocSeq = service.selectTimeTableSeq(deliverymanSubway);
			// 2-3) 사용자 위치와 배송원 위치 비교 후 가장 가까운 위치의 배송원 찾기
			if(userLocSeq != deliverymanLocSeq) {
				if(userLocSeq > deliverymanLocSeq) {
					deliverymanCnt = userLocSeq - deliverymanLocSeq;
				}else {
					deliverymanCnt = (10-deliverymanLocSeq)+(0+userLocSeq);	// 전체 역 10개일 경우를 가정
				}
				if(deliverymanCnt < num) {
					if(deliverymanCnt > 0) {
						num = deliverymanCnt;
					}
				}
				if(deliverymanCnt == num) {
					delManInfo.put("deliverymanId", deliverymanId);
					delManInfo.put("deliverymanName", deliverymanName);
					delManInfo.put("deliverymanSubway", deliverymanSubway);
					delManInfo.put("deliverymanLocSeq", deliverymanLocSeq);
					System.out.println("숫자는..? "+deliverymanCnt);
				}
			}
		}
		System.out.println("배송원 정보 확인!! "+delManInfo);
		//  3) 배송원 물량 확인
		int deliveryQty = service.selectDeliveryQty((String)delManInfo.get("deliverymanId"));
		System.out.println("배송 물량 확인!! "+deliveryQty);
		//  4) 배송원 현재 위치 > 사용자 보관함 시간 계산
		int delManTime;
		if(deliveryQty < 7) {
			Map<String, Integer> userDelMan = new HashMap<String, Integer>();
			if(userLocSeq > (Integer)delManInfo.get("deliverymanLocSeq")) {
				userDelMan.put("startSeq", (Integer)delManInfo.get("deliverymanLocSeq"));
				userDelMan.put("arriveSeq", userLocSeq-1);
				delManTime = service.selectDeliveryTime(userDelMan);
				System.out.println("지하철 번호 확인!! "+userDelMan);
				System.out.println("배송원 도착 시간 확인!! "+delManTime);
			}else {
				userDelMan.put("startSeq", (Integer)delManInfo.get("deliverymanLocSeq")-1);
				userDelMan.put("arriveSeq", 9);											// 전체 역 10개일 경우를 가정
				int arroundTime1 = service.selectDeliveryTime(userDelMan);
				Map<String, Integer> userDelMan1 = new HashMap<String, Integer>();
				int arroundTime2;
				if(userLocSeq != 1) {
					userDelMan1.put("startSeq", 1);
					userDelMan1.put("arriveSeq", userLocSeq-1);
					arroundTime2 = service.selectDeliveryTime(userDelMan);
				}else {
					arroundTime2 = 0;
				}
				delManTime = arroundTime1+arroundTime2;
				System.out.println("배송원 도착 시간 확인!! "+delManTime);
			}
		}else {
			delManTime = -1;
		}
		
		// 2. 사용자 보관함 > 배송 보관함 시간/비용 계산
		Map<String, Integer> userDelLoc = new HashMap<String, Integer>();
		int userDelTime;
		int deliveryCost;
		if(userLocSeq < deliveryLocSeq) {
			userDelLoc.put("startSeq", userLocSeq);
			userDelLoc.put("arriveSeq", deliveryLocSeq-1);
			userDelTime = service.selectDeliveryTime(userDelLoc);
			deliveryCost = (deliveryLocSeq - userLocSeq) * 200;					// 배송비용 = 정거장 수 * 200원
			System.out.println("지하철 번호 확인!! "+userDelLoc);
			System.out.println("배송 시간 확인!! "+userDelTime);
			System.out.println("배송 비용 확인!! "+deliveryCost);
		}else {
			userDelLoc.put("startSeq", userLocSeq-1);
			userDelLoc.put("arriveSeq", 10);									// 전체 역 10개일 경우를 가정
			int arroundTime1 = service.selectDeliveryTime(userDelLoc);
			int arroundCost1 = (10 - userLocSeq) * 200;
			Map<String, Integer> userDelLoc1 = new HashMap<String, Integer>();
			int arroundTime2;
			int arroundCost2;
			if(userLocSeq != 1) {
				userDelLoc1.put("startSeq", 1);
				userDelLoc1.put("arriveSeq", deliveryLocSeq-1);
				arroundTime2 = service.selectDeliveryTime(userDelLoc1);
				arroundCost2 = (deliveryLocSeq - 1) * 200;
			}else {
				arroundTime2 = 0;
				arroundCost2 = 0;
			}
			userDelTime = arroundTime1+arroundTime2;
			deliveryCost = arroundCost1 + arroundCost2;
			System.out.println("배송 시간 확인!! "+userDelTime);
			System.out.println("배송 비용 확인!! "+deliveryCost);
		}
		
		// 3. 배송원 현재 위치 > 사용자 보관함 > 배송 보관함 시간 합산
		int deliveryTime = delManTime + userDelTime;
		
		// 4. 화면에 전달할 값 저장
		Map<String, Object> info = new HashMap<String, Object>();
		if(delManTime != -1) {
			info.put("boxSeq", userLocSeq);
			info.put("storageId", userStorageId);
			info.put("userStorageSubway", userStorageSubway);
			info.put("deliveryStorageSubway", deliveryStorageSubway);
			info.put("outboxId", arriveStation);
			info.put("deliveryTime", deliveryTime);
			info.put("deliveryCost", deliveryCost);
			info.put("delManInfo", delManInfo);
			info.put("isc", "success");
		}else {
			info.put("isc", "false");
		}
		logger.info("Controller_checkDeliveryInfo.do 실행");
		return info;
	}
	
	/**
	 * 배송 등록
	 * @param delDto
	 * @param goodsDto
	 * @return
	 */
	@RequestMapping(value = "/delivery.do", method = RequestMethod.POST)
	public String delivery(DeliveryDto delDto, StorageGoodsDto goodsDto) {
		System.out.println("@@@@@@DeliveryDto@@@@@"+delDto);
		System.out.println("@@@@@@StorageGoodsDto@@@@@"+goodsDto);
		boolean isc = service.insertDelivery(delDto, goodsDto);
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
