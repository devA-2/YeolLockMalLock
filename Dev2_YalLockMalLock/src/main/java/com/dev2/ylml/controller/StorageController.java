package com.dev2.ylml.controller;

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
	
//	지도에 보관함 위치 마커표시하기  + 검색창에 자동완성 구현하기
	@RequestMapping(value = "/map.do")
	public String showMap(Model model) {
		
		List<Map<String,Object>> positions = service.selectMap();
		//지도 표시
		System.out.println(positions);
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < positions.size(); i++) {
	            JSONObject data = new JSONObject();
	            data.put("id",positions.get(i).get("ID"));
	            data.put("latlng", "new kakao.maps.LatLng("+positions.get(i).get("LATLNG")+")");
	            jsonArray.add(i, data);    
		 }
		
		
//		JSONArray jsonArray = new JSONArray();
//		for(Map<String,Object> map:positions) {
//			jsonArray.add(getJsonStringFromMap(map));
//		}
		
//        ObjectMapper objectMapper = new ObjectMapper();
//        String positionJson="";
//		try {
//			positionJson = objectMapper.writeValueAsString(positions);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		log.info(positionJson);
        
//        JSONArray positionJson = new JSONArray(positions);
		model.addAttribute("positions",jsonArray);
		
		// 검색창에 자동완성 위한 list 받아오기
		List<StorageListDto> list = service.selectStorageList();
		model.addAttribute("list",list);
		return "map";
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxCountStorage.do")
	public StorageListDto ajaxCountStorage(String id) {
		StorageListDto LDto = service.ajaxCountStorage(id);
		log.info("받아온 Storage List Dto : "+ LDto);
		return LDto;
	}
	
	@RequestMapping(value = "/selectStorageStatus.do",method = RequestMethod.GET)
	public String selectStorageStatus(String id,Model model) {
		List<StorageBoxDto> statusList = service.selectStorageStatus(id);
		model.addAttribute("statusList",statusList);
		return "map";
	}
	
	
	
}
