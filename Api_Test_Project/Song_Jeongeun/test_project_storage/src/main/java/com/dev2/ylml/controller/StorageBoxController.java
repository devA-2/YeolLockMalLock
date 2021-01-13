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
import com.dev2.ylml.dto.DeliveryListDto;
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
	
//	========================= 버튼 눌렀을 때 세션 저장 =========================
	
	/**
	 * 교환/연장/결제 버튼 눌렀을 때 작동
	 * @param boxSeq
	 * @param storageId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/dtoSession.do", method = RequestMethod.POST)
	@ResponseBody
	public String dtoSession(@RequestParam("boxSeq") int boxSeq, @RequestParam("storageId") String storageId, HttpSession session) {
		Map<String, Object> seqId = new HashMap<String, Object>();
		seqId.put("boxSeq", boxSeq);
		seqId.put("storageId", storageId);
		StorageGoodsDto storageGoodsDto = service.selectStorageGoods(seqId);
		System.out.println("storageGoodsDto확인!!  "+storageGoodsDto);
		session.setAttribute("storageGoodsDto", storageGoodsDto);
		logger.info("Controller_keyTransBtn.do 실행");
		return "done";
	}
	
	/**
	 * 배송 버튼 눌렀을 때 작동
	 * 카테고리 상태에 따라 분기 나누기, 배송 시 StorageGoodsDto 세션 생성
	 * @param storageId
	 * @param boxSeq
	 * @param categoryCode
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deliveryBtn.do", method = RequestMethod.POST)
	@ResponseBody
	public String deliveryBtn(@RequestParam("boxSeq") int boxSeq, @RequestParam("storageId") String storageId, @RequestParam("categoryCode") String categoryCode, HttpSession session) {
		String result;
		if(categoryCode.equals("D") || categoryCode.equals("RD")) {
			System.out.println(categoryCode);
			result = "false";
		}else {
			System.out.println(categoryCode);
			Map<String, Object> seqId = new HashMap<String, Object>();
			seqId.put("boxSeq", boxSeq);
			seqId.put("storageId", storageId);
			StorageGoodsDto storageGoodsDto = service.selectStorageGoods(seqId);
			System.out.println("storageGoodsDto확인!!  "+storageGoodsDto);
			session.setAttribute("storageGoodsDto", storageGoodsDto);
			result = "success";
		}
		logger.info("Controller_deliveryBtn.do 실행");
		return result;
	}
	
//	============================== 배송 ==============================
	
	
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
		// 사용자 현재 보관함 정보
		StorageGoodsDto storageGoodsDto = (StorageGoodsDto) session.getAttribute("storageGoodsDto");
		System.out.println("storageGoodsDto확인!!  "+storageGoodsDto);
		String userStorageId = storageGoodsDto.getStorageId();
		StorageBoxListDto userSBListDto = service.selectStorageBoxList(userStorageId);
		String userStorageSubway = userSBListDto.getSubway();
		int userLocSeq = storageGoodsDto.getBoxSeq();
		
		// 배송역 정보
		StorageBoxListDto deliverySBListDto = service.selectStorageBoxList(arriveStation);
		String deliveryStorageSubway = deliverySBListDto.getSubway();
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
	public String delivery(DeliveryDto delDto, String message, HttpSession session) {
		StorageGoodsDto storageGoods = (StorageGoodsDto) session.getAttribute("storageGoodsDto");
		storageGoods.setMessage(message);
		System.out.println("보관함 정보 확인!! " +storageGoods);
		boolean isc = service.insertDelivery(delDto, storageGoods);
		logger.info("Controller_delivery.do 실행");
		return isc?"redirect:/deliverySuccess.do":"redirect:/userStorageList.do";
	}

	@RequestMapping(value = "/deliverySuccess.do", method = RequestMethod.GET)
	public String deliverySuccess(HttpSession session) {
		session.removeAttribute("storageGoodsDto");
		logger.info("Controller_deliverySuccess.do 실행");
		return "delivery/deliverySuccess";
	}

//	============================== 메뉴-배송(사용자) ==============================	
	
	/**
	 * 메뉴 > 배송 메인
	 * @return
	 */
	@RequestMapping(value = "/deliveryListMain.do", method = RequestMethod.GET)
	public String moveDelivery() {
		logger.info("Controller_moveDelivery.do 실행");
		return "deliveryList/deliveryListMain";
	}
	
	/**
	 * 배송 메인 > 배송 시간/비용 조회
	 * @return
	 */
	@RequestMapping(value = "/deliveryInquiry.do", method = RequestMethod.GET)
	public String deliveryInquiry() {
		logger.info("Controller_deliveryInquiry.do 실행");
		return "deliveryList/deliveryInquiry";
	}
	
	/**
	 * storageId로 역 이름 조회
	 * @param storageId
	 * @return
	 */
	@RequestMapping(value = "/searchSubway.do", method = RequestMethod.POST)
	@ResponseBody
	public StorageBoxListDto searchSubway(@RequestParam("storageId") String storageId) {
		StorageBoxListDto SBDto = service.selectStorageBoxList(storageId);
		return SBDto;
	}
	
	@RequestMapping(value = "/inquiryDelivery.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> inquiryDelivery(@RequestParam("startStation") String startStation, @RequestParam("arriveStation") String arriveStation) {
		// 역 SEQ
		int startSeq = service.selectTimeTableSeq(startStation);
		int arriveSeq = service.selectTimeTableSeq(arriveStation);
		
		// 출발 보관함 > 도착 보관함 시간/비용 계산
		Map<String, Integer> sationSeqs1 = new HashMap<String, Integer>();
		int deliveryTime;
		int deliveryCost;
		if(startSeq < arriveSeq) {
			sationSeqs1.put("startSeq", startSeq);
			sationSeqs1.put("arriveSeq", arriveSeq-1);
			deliveryTime = service.selectDeliveryTime(sationSeqs1);
			deliveryCost = (arriveSeq - startSeq) * 200;					// 배송비용 = 정거장 수 * 200원
			System.out.println("배송 시간 확인!! "+deliveryTime);
			System.out.println("배송 비용 확인!! "+deliveryCost);
		}else {
			sationSeqs1.put("startSeq", startSeq-1);
			sationSeqs1.put("arriveSeq", 10);									// 전체 역 10개일 경우를 가정
			int arroundTime1 = service.selectDeliveryTime(sationSeqs1);
			int arroundCost1 = (10 - startSeq) * 200;
			Map<String, Integer> sationSeqs2 = new HashMap<String, Integer>();
			int arroundTime2;
			int arroundCost2;
			if(startSeq != 1) {
				sationSeqs2.put("startSeq", 1);
				sationSeqs2.put("arriveSeq", arriveSeq-1);
				arroundTime2 = service.selectDeliveryTime(sationSeqs2);
				arroundCost2 = (arriveSeq - 1) * 200;
			}else {
				arroundTime2 = 0;
				arroundCost2 = 0;
			}
			deliveryTime = arroundTime1+arroundTime2;
			deliveryCost = arroundCost1 + arroundCost2;
			System.out.println("배송 시간 확인!! "+deliveryTime);
			System.out.println("배송 비용 확인!! "+deliveryCost);
		}
		
		// 화면에 전달할 값 저장
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("deliveryTime", deliveryTime);
		info.put("deliveryCost", deliveryCost);
		return info;
	}
	
	/**
	 * 배송 메인 > 배송 조회(사용자)
	 * 배송 메인 > 배송 조회(배송원)
	 * @param email
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deliveryList.do", method = RequestMethod.GET)
	public String userDeliveryList(String email, String auth, Model model) {
		List<DeliveryListDto> deliveryList = service.selectDeliveryList(email, auth);
		model.addAttribute("deliveryList", deliveryList);
		model.addAttribute("auth", auth);
		System.out.println("DTO 확인!!"+deliveryList);
		logger.info("Controller_checkDeliveryInfo.do 실행");
		return "deliveryList/deliveryList";
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
		logger.info("Controller_pickUp.do 실행");
		return "success";
	}
	
}
