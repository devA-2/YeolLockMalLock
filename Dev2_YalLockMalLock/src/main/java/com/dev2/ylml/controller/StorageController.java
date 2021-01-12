package com.dev2.ylml.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.model.service.StorageIService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class StorageController {
	
	@Autowired
	private StorageIService service;
	

	// 검색창에 자동완성 위한 전체보관함 list 받아오기
	@RequestMapping(value = "/map.do")
	public String showMap(Model model) {
		List<StorageListDto> list = service.selectStorageList();
		model.addAttribute("list",list);
		return "map";
	}
	
	//지도 표시하기위한 ajax 
	@RequestMapping(value = "/selectMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectMap(){
		List<Map<String,Object>> position = service.selectMap();
		return position;
	}
	
	//보관함 마커 클릭시 정보 + 사용가능한 갯수 출력
	@ResponseBody
	@RequestMapping(value = "/ajaxCountStorage.do")
	public StorageListDto ajaxCountStorage(String id) {
		StorageListDto LDto = service.ajaxCountStorage(id);
		log.info("받아온 Storage List Dto : "+ LDto);
		return LDto;
	}
	
	//해당 보관함 현재 사용 여부 가져오기
	@RequestMapping(value = "/selectStorageStatus.do",method = RequestMethod.GET)
	public String selectStorageStatus(String id,Model model) {
		List<StorageBoxDto> statusList = service.selectStorageStatus(id);
		model.addAttribute("id",id);
		model.addAttribute("statusList",statusList);
		return "storageStatus";
	}
	//보관함 등록(나중에 키 update필요함)
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
	//연장하기
	@RequestMapping(value = "/updateExtend.do",method = RequestMethod.POST)
	public String updateExtend(String id, int boxSeq) {
		log.info("boxSeq : "+boxSeq+",id : "+id);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("boxSeq", boxSeq);
		map.put("id", id);
		boolean isc = service.updateExtend(map);
		log.info("연장 결과 : "+isc);		
		return "index";
	}
	
	
}
