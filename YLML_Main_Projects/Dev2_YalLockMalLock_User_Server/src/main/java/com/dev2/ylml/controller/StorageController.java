package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.model.service.UserIService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/storage")
public class StorageController {
	
	@Autowired
	private UserIService service;

	/**
	 * 지도+ 검색창 페이지로 이동
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/map.do")
	public String showMap() {
		return "storage/map";
	}
	/**
	 * 검색창에 자동완성 위한 전체보관함 list 받아오기
	 * @param 
	 * @return storage_id AS "value" 
	 * @return storage_name AS "label" 
	 * @return address||' '||subway||' '||detail AS "desc"
	 */
	@ResponseBody
	@RequestMapping(value = "/selectStorageList.do")
	public List<Map<String,String>> selectStorageList(HttpSession session) {
		List<Map<String,String>> list = service.selectStorageList();
		session.setAttribute("list", list);
		//이름 직관적으로 받아와서 바꿔주는게 나은지 ?
		return list;
	}
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
	/**
	 * ajax - 보관함 마커 클릭시 정보 + 사용가능한 갯수 출력
	 * @param id
	 * @return StorageListDto
	 */
	@ResponseBody
	@RequestMapping(value = "/ajaxCountStorage.do")
	public StorageListDto ajaxCountStorage(String id) {
		StorageListDto LDto = service.ajaxCountStorage(id);
		log.info("받아온 Storage List Dto : "+ LDto);
		return LDto;
	}
	/**
	 * 해당 보관함 현재 사용 여부 가져오기
	 * @param id
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectStorageStatus.do",method = RequestMethod.GET)
	public String selectStorageStatus(String id,Model model,HttpSession session) {
		//해당 id의 보관함 사용여부 가져오기
		List<StorageBoxDto> statusList = service.selectStorageStatus(id);
		//세션에서 보관함 이름, 주소정보 받아오기
		List<Map<String,String>> list = (List<Map<String, String>>) session.getAttribute("list");
		//세션에서 id와 같은 보관함 정보 model에 담기
		for(int i=0;i<list.size();i++) {
			if(id.equals(list.get(i).get("value"))){
				//value는 id, label은 name, desc는 주소(jqueryUI autocomplete 사용 때문)
				model.addAttribute("storageInfo",list.get(i));
				break;
			}
		}
		model.addAttribute("statusList",statusList);
		return "storage/storageStatus";
	}
	
	/**
	 * box,Seq,email session에 담고 보관정보 확인하기 
	 * @param boxSeq
	 * @param id
	 * @param email
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/storageInfoCheck.do",method = RequestMethod.POST)
	public String storageInfoCheck(int boxSeq, String id, String email,HttpSession session) {
		log.info(boxSeq+","+id+" "+email);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boxSeq", boxSeq);
		map.put("id", id);
		map.put("email", email);
		session.setAttribute("map", map);
		return "storage/storageInfoCheck";
	}
	/**
	 * 보관정보 확인하고 NFC 태그페이지로이동 
	 * @return
	 */
	@RequestMapping(value = "/NFCtag.do",method = RequestMethod.GET)
	public String NFCtag() {
		log.info("보관전 NFC태그 화면으로 이동");
		return "storage/compareKey";
	}
	

	/**
	 * TODO 보관 물품 정보에 key 등록해야함
	 * 보관물품 등록
	 * @param boxSeq
	 * @param id
	 * @param email
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/insertGoods.do",method = RequestMethod.POST)
	public String insertGoods(String NFC,HttpSession session) {
		log.info("NFC태그값 : "+NFC);
		//nfc 값 받아서 수정해야함
		Map<String,Object> map = (Map<String, Object>) session.getAttribute("map");
		log.info("세션에서 받은 map은 ? "+map);
		boolean isc = service.insertGoods(map);
		log.info("insertGoods 결과는 ? "+ isc);
		session.removeAttribute("map");
		return "storage/closeDoor";
	}
	/**
	 * 연장하기
	 * @param id
	 * @param boxSeq
	 * @return
	 */
	@RequestMapping(value = "/updateExtend.do",method = RequestMethod.POST)
	public String updateExtend(@RequestParam("storageId") String id, int boxSeq) {
		log.info("boxSeq : "+boxSeq+",id : "+id);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boxSeq", boxSeq);
		map.put("id", id);
		boolean isc = service.updateExtend(map);
		log.info("연장 결과 : "+isc);		
		return "redirect:/storage/userStorageList.do";
	}
	/**
	 * 추가비용 가지고 키대조 화면으로 이동하는 컨트롤러
	 * @param overCost
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/compareKey.do",method = RequestMethod.GET)
	public String compareKey(int overCost,Model model) {
		log.info("키 대조 화면으로 이동");
		model.addAttribute("overCost",overCost);
		return "storage/compareKey";
	}
	/**
	 * 결제전 키 대조, 불일치시 페이지이동
	 * 할증비용 있을때 결제전 할증비용 추가
	 * @param key
	 * @param overCost
	 * @return
	 */
	@RequestMapping(value = "/beforePay.do",method = RequestMethod.POST)
	public String beforePay(String key,int overCost,Model model) {
		log.info("받은 key : "+key + " overCost : "+overCost);
		String costCode = service.compareKey(key);
		log.info("키 대조 결과 (결제코드) : "+costCode);
		
		if(costCode == null||costCode.isBlank()) {
			log.info("해당 키가 없음 -> 키 대조 실패 -> 전단계?메인페이지?");
			return "storage/index";
		}
		
		if(overCost>0) {
			log.info("보관만료시간 초과");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("costCode", costCode);
			map.put("overCost", overCost);
			boolean isc = service.updateExtraCost(map);
			log.info("보관시간 만료 이후 할증 금액 수정 결과 : "+ isc);
		}
		model.addAttribute("costCode",costCode);
		//dto->costCode로 수정했으니 jsp에서도 확인해주기
		return "storage/payPage";
	}
	
	/**
	 * ajax 수령 사용자 이메일 확인
	 * @param email
	 * @return email
	 */
	@ResponseBody
	@RequestMapping(value="/checkOutUser.do",method = RequestMethod.GET)
	public String checkOutEmail(String email) {
		System.out.println(email+"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
		String checkedEmail = service.checkOutEmail(email);
		log.info("수령 사용자 이메일 확인 -> "+email+ " 받아온 이메일 : "+ checkedEmail);
		return checkedEmail;
	}
	/**
	 * TODO 키 udpate!!
	 * 이메일이 없으면 이메일입력 폼으로
	 * 이메일이 있으면 수령사용자 이메일 등록
	 * @param id
	 * @param boxSeq
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/updateOutUser.do",method = RequestMethod.POST)
	public String updateOutUser(Model model,@RequestParam("storageId") String id,
			int boxSeq, @RequestParam(required=false) String email) {
		log.info("받아온 id: "+id+" boxSeq: "+boxSeq+" outUSerEmail: "+email);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boxSeq", boxSeq);
		map.put("id", id);
		if(email == null || email.isBlank()) {
			//이메일이 없으면 수령 사용자 이메일 입력 폼으로
//			model.addAttribute("id",id);
//			model.addAttribute("boxSeq",boxSeq);
			model.addAttribute("map",map);
			return "storage/outUserForm";
		}else {
			//이메일이 있으면 OutUser 등록 
			//TODO key update 같이 해주기 
			map.put("email", email);
			boolean isc = service.updateOutUser(map);
			log.info("수령사용자 등록 결과 : "+isc);
			return "storage/map";
		}
	}
	/**
	 * 결제후 반품여부 없으면 결제완료페이지로 이동
	 * 결제페이지에서 반품여부 Y -> 반품페이지로 이동
	 * 결제페이지에서 반품여부 N -> 보관함 사용가능 처리, 보관물품 정보 삭제
	 * @param costCode, returnFlag
	 * @return
	 */
	@RequestMapping(value = "/afterPayment.do",method = RequestMethod.POST)
	public String afterPayment(String returnFlag,String costCode,Model model) {
		if(returnFlag==null ||returnFlag.isBlank()) {
			//반품여부 없으면 결제 완료 페이지로
			model.addAttribute("costCode",costCode);
			return "storage/afterPayment";
		}else if(returnFlag.equals("Y")) {
			//반품여부 확인-> 결제코드 담고 반품페이지로
			model.addAttribute("costCode",costCode);
			return "storage/returnPage";
		}else {
			//반품여부 N 이면 사용끝!
			Map<String,String> map = new HashMap<String, String>();
			map.put("costCode", costCode);
			boolean isc = service.afterPayment(map);
			log.info("보관함 사용가능처리 + 보관 정보 삭제 결과 : "+isc);
			return "storage/map";
		}
	}
	
	/**
	 * 결제후 반품 메세지 입력후 반품등록
	 * @param map(costCode,message)
	 * @return
	 */
	@RequestMapping(value = "/insertReturn.do",method = RequestMethod.POST)
	public String insertReturn(String costCode, String message) {
		log.info("반품 Controller - costCode : "+costCode + " message : "+message);
		Map<String, String> map = new HashMap<String, String>();
		map.put("costCode",costCode );
		map.put("message", message);
		boolean isc = service.insertReturn(map);
		log.info("반품 등록 service 결과 : "+isc);
		return "storage/map";
	}

	
	
	
	/**
	 * 보관 정보 조회(사용자)
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/userStorageList.do", method = RequestMethod.GET)
	public String userStorageList(Model model, HttpSession session) {
//		MemberDto mDto = (MemberDto) session.getAttribute("mem");
//		String email = mDto.getEmail();
		String email = "user01@naver.com";
		System.out.println("이메일 확인 !! "+email);
		List<UserStorageListDto> storageList = service.selectUserStorageList(email);
		model.addAttribute("list", storageList);
		log.info("Controller_userStorageList.do 실행");
		return "storage/userStorageList";
	}
	
	/**
	 * 보관 정보 > 배송 이용 약관
	 * StorageGoodsDto 세션 생성
	 * @return
	 */
	@RequestMapping(value = "/deliveryForm.do", method = RequestMethod.POST)
	public String deliveryForm(StorageGoodsDto dto, HttpSession session) {
		Map<String, Object> seqId = new HashMap<String, Object>();
		seqId.put("boxSeq", dto.getBoxSeq());
		seqId.put("storageId", dto.getStorageId());
		StorageGoodsDto storageGoodsDto = service.selectStorageGoods(seqId);
		System.out.println("storageGoodsDto확인!!  "+storageGoodsDto);
		session.setAttribute("storageGoodsDto", storageGoodsDto);
		log.info("Controller_deliveryForm.do 실행");
		return "delivery/deliveryTerms";
	}
	
	/**
	 * 배송 이용 약관 > 도착 장소 선택 및 시간비용 계산
	 * @return
	 */
	@RequestMapping(value = "/searchDeliveryStation.do", method = RequestMethod.GET)
	public String searchDeliveryStation() {
		log.info("Controller_searchDeliveryStation.do 실행");
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
		StorageListDto userSBListDto = service.selectStorageBoxList(userStorageId);
		String userStorageSubway = userSBListDto.getSubway();
		int userLocSeq = storageGoodsDto.getBoxSeq();
		
		// 배송역 정보
		StorageListDto deliverySBListDto = service.selectStorageBoxList(arriveStation);
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
					
					if(deliverymanDist < num) {
						num = deliverymanDist;
						
						if(deliverymanDist == num){
						delManInfo.put("deliverymanId", deliverymanId);
						delManInfo.put("deliverymanName", deliverymanName);
						delManInfo.put("deliverymanSubway", deliverymanSubway);
						delManInfo.put("deliverymanLocSeq", deliverymanLocSeq);
						System.out.println("배송원 거리 확인!! "+deliverymanDist);
						}
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
		log.info("Controller_checkDeliveryInfo.do 실행");
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
		log.info("Controller_delivery.do 실행");
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
		log.info("Controller_deliverySuccess.do 실행");
		return "delivery/deliverySuccess";
	}
	
	/**
	 * 메뉴 > 배송 메인
	 * @return
	 */
	@RequestMapping(value = "/deliveryListMain.do", method = RequestMethod.GET)
	public String moveDelivery() {
		log.info("Controller_moveDelivery.do 실행");
		return "delivery/deliveryListMain";
	}
	
	/**
	 * 배송 메인 > 배송 시간/비용 조회
	 * @return
	 */
	@RequestMapping(value = "/deliveryInquiry.do", method = RequestMethod.GET)
	public String deliveryInquiry() {
		log.info("Controller_deliveryInquiry.do 실행");
		return "delivery/deliveryInquiry";
	}
	
	/**
	 * storageId로 역 이름 조회
	 * @param storageId
	 * @return
	 */
	@RequestMapping(value = "/searchSubway.do", method = RequestMethod.POST)
	@ResponseBody
	public StorageListDto searchSubway(@RequestParam("storageId") String storageId) {
		StorageListDto storageListDto = service.selectStorageBoxList(storageId);
		log.info("Controller_searchSubway.do 실행");
		return storageListDto;
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
		log.info("Controller_inquiryDelivery.do 실행");
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
	public String userDeliveryList(Model model, HttpSession session) {
//		MemberDto mDto = (MemberDto) session.getAttribute("mem");
//		String email = mDto.getEmail();
//		String auth = Integer.toString(mDto.getAuth());
		String email = "user01@naver.com";
		String auth = "10";
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("auth", auth);
		System.out.println("map 확인!! "+map);
		List<DeliveryDto> deliveryList = service.selectDeliveryList(map);
		model.addAttribute("deliveryList", deliveryList);
		model.addAttribute("auth", auth);
		System.out.println("DTO 확인!!"+deliveryList);
		log.info("Controller_checkDeliveryInfo.do 실행");
		return "delivery/deliveryList";
	}
	
	// TODO : 추후 StorageGoodsDto 세션 생성으로 변경해야 함
		@RequestMapping(value = "/paymentPage.do", method = RequestMethod.GET)
		public String paymentPage(String phoneNum, String cost, String costCode, HttpSession session) {
			session.setAttribute("phoneNum", phoneNum);
			session.setAttribute("cost", cost);
			session.setAttribute("costCode", costCode);
			log.info("Controller_paymentPage.do 실행");
			return "storage/payment";
		}
		
		@RequestMapping(value = "/afterPayment.do", method = RequestMethod.GET)
		public String afterPayment(String imp_success, HttpSession session) {
			String costCode = (String) session.getAttribute("costCode");
			String result;
			if(imp_success.equals(true)) {
				service.updateCostStatus(costCode);
				result = "redirect:./successPayment.do";
			}else {
				result = "redirect:./falsePayment.do";
			}
			log.info("Controller_afterPayment.do 실행");
			return result;
		}
		
		@RequestMapping(value = "/successPayment.do", method = RequestMethod.GET)
		public String successPayment() {
			log.info("Controller_successPayment.do 실행");
			return "storage/successPayment";
		}
		
		@RequestMapping(value = "/falsePayment.do", method = RequestMethod.GET)
		public String falsePayment() {
			log.info("Controller_falsePayment.do 실행");
			return "storage/falsePayment";
		}
	
}
