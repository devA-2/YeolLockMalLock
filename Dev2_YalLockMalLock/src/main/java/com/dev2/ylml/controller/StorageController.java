package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.dto.UserGoodsDto;
import com.dev2.ylml.model.service.StorageIService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StorageController {
	
	@Autowired
	private StorageIService service;

	/**
	 * 지도+ 검색창 페이지로 이동
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/map.do")
	public String showMap() {
		return "map";
	}
	/**
	 * 검색창에 자동완성 위한 전체보관함 list 받아오기
	 * @param 
	 * @return storage_id AS "value" ,storage_name AS "label" ,address||' '||subway||' '||detail AS "desc"
	 */
	@ResponseBody
	@RequestMapping(value = "/selectStorageList.do")
	public List<Map<String,String>> selectStorageList() {
		List<Map<String,String>> list = service.selectStorageList();
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
//		log.info(position+"");
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
	@RequestMapping(value = "/selectStorageStatus.do",method = RequestMethod.GET)
	public String selectStorageStatus(String id,Model model) {
		List<StorageBoxDto> statusList = service.selectStorageStatus(id);
		model.addAttribute("id",id);
		model.addAttribute("statusList",statusList);
		return "storageStatus";
	}
	
	/**
	 * TODO 보관 물품 정보에 key 등록해야함
	 * 보관물품 등록
	 * @param boxSeq
	 * @param id
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/insertGoods.do",method = RequestMethod.POST)
	public String insertGoods(int boxSeq, String id, String email) {
		log.info(boxSeq+","+id+" "+email);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boxSeq", boxSeq);
		map.put("id", id);
		map.put("email", email);
		log.info("map은 ?" + map);
		boolean isc = service.insertGoods(map);
		log.info("insertGoods 결과는 ? "+ isc);
		return "index";
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
		return "index";
	}
	/**
	 * 추가비용 가지고 키대조 화면으로 이동하는 컨트롤러
	 * @param overCost
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/compareKey.do",method = RequestMethod.GET)
//	public String compareKey(@RequestParam("storageId") String id,int boxSeq, int overCost,Model model) {
	public String compareKey(int overCost,Model model) {
		log.info("키 대조 화면으로 이동");
		//추후 id, boxSeq 필요시 map 으로 던지기
		model.addAttribute("overCost",overCost);
		return "compareKey";
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
		UserGoodsDto goodsDto = service.compareKey(key);
		log.info("키 대조 결과 : "+goodsDto);
		
		if(goodsDto == null) {
			log.info("해당 키가 없음 -> 키 대조 실패 -> 전단계?메인페이지?");
			return "index";
		}
		
		if(overCost>0) {
			log.info("보관만료시간 초과");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("costCode", goodsDto.getCostCode());
			map.put("overCost", overCost);
			boolean isc = service.updateExtraCost(map);
			log.info("보관시간 만료 이후 할증 금액 수정 결과 : "+ isc);
		}
		model.addAttribute("dto",goodsDto);//costCode,outUser담겨있는 dto
		return "payPage";
	}
	/**
	 * 수령 사용자 이메일 입력 폼으로 이동
	 * @return
	 */
	@RequestMapping(value="/outUserForm.do",method = RequestMethod.POST)
	public String outUserForm() {
		log.info("수령 사용자 이메일 입력하는 폼으로 이동하는 컨트롤러");
		return "outUserEmailCheck";
	}
	
	/**
	 * 수령 사용자 이메일 확인
	 * @param email
	 * @return email
	 */
	@ResponseBody
	@RequestMapping(value="/checkOutUser.do",method = RequestMethod.GET)
	public String checkOutEmail(String email) {
		String checkedEmail = service.checkOutEmail(email);
		log.info("수령 사용자 이메일 확인 -> "+email+ " 받아온 이메일 : "+ checkedEmail);
		return checkedEmail;
	}
	/**
	 * TODO 키 udpate
	 * 받아온 수령사용자 이메일 등록
	 * @param id
	 * @param boxSeq
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/updateOutUser.do",method = RequestMethod.GET)
	public String updateOutUser(String id,int boxSeq,String email) {
		log.info("받아온 id: "+id+" boxSeq: "+boxSeq+" outUSerEmail: "+email);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boxSeq", boxSeq);
		map.put("id", id);
		map.put("email", email);
		boolean isc = service.updateOutUser(map);
		log.info("수령사용자 등록 결과 : "+isc);
		return "map";
	}
	/**
	 * 결제 후 보관함 사용가능 처리, 보관물품 정보 삭제
	 * @param costCode
	 * @return
	 */
	@RequestMapping(value = "/afterPayment.do",method = RequestMethod.POST)
	public String afterPayment(String costCode) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("costCode", costCode);
		boolean isc = service.afterPayment(map);
		log.info("보관함 사용가능처리 + 보관 정보 삭제 결과 : "+isc);
		return "index";
	}
	/**
	 * 결제후 반품 메세지 입력후 반품등록
	 * @param costCode
	 * @param message
	 * @return
	 */
	@RequestMapping(value = "/insertReturn.do",method = RequestMethod.POST)
	public String insertReturn(String costCode, String message) {
		log.info("반품 Controller - costCode : "+costCode + " message : "+message);
		boolean isc = service.insertReturn(costCode, message);
		log.info("반품 등록 service 결과 : "+isc);
		return "map";
	}

	
}
