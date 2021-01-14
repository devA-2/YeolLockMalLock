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
		String isc;	// 이미 배송한 경우를 위한 분기 값
		if(categoryCode.equals("D") || categoryCode.equals("RD")) {
			System.out.println(categoryCode);
			isc = "false";
		}else {
			System.out.println(categoryCode);
			Map<String, Object> seqId = new HashMap<String, Object>();
			seqId.put("boxSeq", boxSeq);
			seqId.put("storageId", storageId);
			StorageGoodsDto storageGoodsDto = service.selectStorageGoods(seqId);
			System.out.println("storageGoodsDto확인!!  "+storageGoodsDto);
			session.setAttribute("storageGoodsDto", storageGoodsDto);
			logger.info("세션 확인!! "+session.getAttribute("storageGoodsDto"));
			isc = "success";
		}
		logger.info("Controller_deliveryBtn.do 실행 {}");
		return isc;
	}
	
//	============================== 배송 ==============================
	
	/**
	 * 보관 정보 > 배송 이용 약관
	 * @return
	 */
	@RequestMapping(value = "/deliveryForm.do", method = RequestMethod.GET)
	public String deliveryForm() {
		logger.info("Controller_deliveryForm.do 실행");
		return "delivery/deliveryTerms";
	}
	
	/**
	 * 배송 이용 약관 > 도착 장소 선택 및 시간비용 계산
	 * @return
	 */
	@RequestMapping(value = "/searchDeliveryStation.do", method = RequestMethod.GET)
	public String searchDeliveryStation() {
		logger.info("Controller_searchDeliveryStation.do 실행");
		return "delivery/searchDeliveryStation";
	}
	
	/**
	 * 배송 거리/비용 조회를 위한 Ajax
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
		
		// 최종 return 값
		Map<String, Object> info = new HashMap<String, Object>();
		
		if(userStorageSubway.equals(deliveryStorageSubway)) {	// 사용자가 현재 위치와 동일한 장소를 선택했을 경우
			info.put("isc", "same");
		} else {												// 사용자가 현재 위치와 다른 장소를 선택했을 경우
			// 1. 배송원 현재 위치 > 사용자 보관함 거리 계산
			//  1) 전체 배송원 조회
			List<MemberDto> deliveryMans = service.selectDeliveryMan();
			//  2) 사용자 보관함과 가까운 배송원 탐색
			int deliverymanDist;								// 사용자 위치 seq - 배송원 위치 seq
			int num = 1000;										// 가까운 배송원을 가리기 위한 허수
			int subwayCnt = service.selectSubwayCnt();			// 전체 역 갯수
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
						deliverymanDist = userLocSeq - deliverymanLocSeq;
					}else {
						deliverymanDist = (subwayCnt-deliverymanLocSeq)+userLocSeq;
					}
					if(deliverymanDist < num && deliverymanDist > 0) {
						num = deliverymanDist;
					}else if(deliverymanDist == num) {
						delManInfo.put("deliverymanId", deliverymanId);
						delManInfo.put("deliverymanName", deliverymanName);
						delManInfo.put("deliverymanSubway", deliverymanSubway);
						delManInfo.put("deliverymanLocSeq", deliverymanLocSeq);
						System.out.println("배송원 거리 확인!! "+deliverymanDist);
					}
				}
			}
			System.out.println("배송원 정보 확인!! "+delManInfo);
			//  3) 배송원 물량 확인
			int deliveryQty = service.selectDeliveryQty((String)delManInfo.get("deliverymanId"));
			System.out.println("배송 물량 확인!! "+deliveryQty);
			//  4) 배송원 현재 위치 > 사용자 보관함 시간 계산
			int delManTime;										// 배송원 > 사용자 보관함 이동 시간
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
					userDelMan.put("arriveSeq", subwayCnt-1);
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
				delManTime = -1;	// 물량 초과로 배송 불가
			}
			
			// 2. 사용자 보관함 > 배송 보관함 시간/비용 계산
			Map<String, Integer> userDelLoc = new HashMap<String, Integer>();
			int userDelTime;											// 사용자 보관함 > 배송 보관함 이동 시간
			int deliveryCost;											// 배송 비용
			if(userLocSeq < deliveryLocSeq) {
				userDelLoc.put("startSeq", userLocSeq);
				userDelLoc.put("arriveSeq", deliveryLocSeq-1);
				userDelTime = service.selectDeliveryTime(userDelLoc);
				deliveryCost = (deliveryLocSeq - userLocSeq) * 200;		// 배송비용 = 정거장 수 * 200원
				System.out.println("배송 시간 확인!! "+userDelTime);
				System.out.println("배송 비용 확인!! "+deliveryCost);
			}else {
				userDelLoc.put("startSeq", userLocSeq-1);
				userDelLoc.put("arriveSeq", subwayCnt);
				int arroundTime1 = service.selectDeliveryTime(userDelLoc);	// 사용자 보관함 seq > 끝 seq 이동 시간
				int arroundCost1 = (subwayCnt - userLocSeq) * 200;			// 사용자 보관함 seq > 끝 seq 이동 비용
				Map<String, Integer> userDelLoc1 = new HashMap<String, Integer>();
				int arroundTime2;											// 처음 seq > 배송 보관함 seq 이동 시간
				int arroundCost2;											// 처음 seq > 배송 보관함 seq 이동 비용
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
			if(delManTime != -1) {	// 물량 초과에 따른 배송 불가 여부 판단
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
	
	/**
	 * 배송 성공 후 보관함 정보 세션 삭제
	 * @param session
	 * @return
	 */
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
	
	/**
	 * 사전 배송 시간 비용 조회 Ajax
	 * 배송원 시간 및 물량 확인 제외
	 * @param startStation
	 * @param arriveStation
	 * @return
	 */
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
		int subwayCnt = service.selectSubwayCnt();		// 전체 역 갯수
		if(startSeq < arriveSeq) {
			sationSeqs1.put("startSeq", startSeq);
			sationSeqs1.put("arriveSeq", arriveSeq-1);
			deliveryTime = service.selectDeliveryTime(sationSeqs1);
			deliveryCost = (arriveSeq - startSeq) * 200;
			System.out.println("배송 시간 확인!! "+deliveryTime);
			System.out.println("배송 비용 확인!! "+deliveryCost);
		}else {
			sationSeqs1.put("startSeq", startSeq-1);
			sationSeqs1.put("arriveSeq", subwayCnt);
			int arroundTime1 = service.selectDeliveryTime(sationSeqs1);		// 사용자 보관함 seq > 끝 seq 이동 시간
			int arroundCost1 = (subwayCnt - startSeq) * 200;				// 사용자 보관함 seq > 끝 seq 이동 비용
			Map<String, Integer> sationSeqs2 = new HashMap<String, Integer>();
			int arroundTime2;												// 처음 seq > 배송 보관함 seq 이동 시간
			int arroundCost2;												// 처음 seq > 배송 보관함 seq 이동 비용
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


//	============================== 지도 복붙 테스트(머지후 삭제) ==============================
// TODO : 지도 구현을 위해 복붙한 메서드 (추후 머지 할 때 selectMap에 대한 컨트롤러, Dao, Service, Mapper 내용 삭제 예정)	
	/**
	 * 지도 표시하기위한 보관함 위치정보 받아오는 ajax
	 * @return storage_id as "id",lng as "lng",lat as "lat"
	 */
	@RequestMapping(value = "/selectMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectMap(){
		List<Map<String,Object>> position = service.selectMap();
		return position;
	}
	
}
