package com.dev2.ylml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev2.ylml.dto.Manager_StorageDto;
import com.dev2.ylml.model.Manager_StorageIService;

@Controller
public class Manager_StorageController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Manager_StorageIService service;
	
	// 보관함 전체 조회 이동 allStorageList.do
	@RequestMapping(value = "allStorageList.do", method = RequestMethod.GET)
	public String StorageList(Model model) {
		logger.info("StorageList.do : 보관함 전체 조회 이동");

//		List<Manager_StorageDto> lists = service.selectAllStorage();
//		model.addAttribute("lists", lists);
		return "StorageList";
	}
	
	// 보관함 전체 조회  storageList
	@RequestMapping(value = "storageList.do", method = RequestMethod.GET)
	public String storageList(Model model, String param) {
		logger.info("storageList.do 보관함 전체 조회 : " + param);
		
		if (param == null) {
			List<Manager_StorageDto> lists = service.selectAllStorage();
			model.addAttribute("lists", lists);
	      }
			else {
				Manager_StorageDto list = service.selectIdStorage(param);
	         model.addAttribute("list", list);
	      }
		return "StorageViewList";
	}
	
	
	//  보관함 ID로 조회 storageDetail.do
	@RequestMapping(value = "storageDetail.do", method = RequestMethod.GET)
	public String storageDetail(Model model,@RequestParam String storage_id) {
		logger.info("storageDetail.do storage_id 검색결과 : " + storage_id);
		
		Manager_StorageDto list = service.selectDetailStorage(storage_id);
		model.addAttribute("list", list);
		return "storageDetail";
	}
	
	// 보관함 상세보기에서 수정페이지 이동 modifyStorage
	@RequestMapping(value = "storageModify.do", method = RequestMethod.GET)
	public String modifyStorage(Model model,@RequestParam String storage_id) {
		logger.info("storageDetail.do 상세보기->수정페이지 이동 : " + storage_id);
		
		Manager_StorageDto list = service.selectDetailStorage(storage_id);
		model.addAttribute("list", list);
		return "storageModify";
	}
	
	//	수정페이지에서 수정등록 storageModifyRegist.do
	@RequestMapping(value = "storageModifyRegist.do", method = RequestMethod.POST)
	public String storageModifyRegist(Model model, Manager_StorageDto dto) {
		logger.info("storageModifyRegist.do 수정내용등록 : " + dto);
		
		service.modifyStorage(dto);
		
		return "redirect:storageDetail.do?storage_id="+dto.getStorage_id();
	}
	
	//	보관함 새로 등록하기 이동 registStorage.do
	@RequestMapping(value = "registStorage.do", method = RequestMethod.GET)
	public String registStorage() {
		logger.info("registStorage.do 보관함등록 이동 : " );
		
		return "storageRegist";
	}
	
	// 보관함 등록하기 실행 registStorage.do
	@RequestMapping(value = "registNewStorage.do", method = RequestMethod.POST)
	public String registStorage(Model model, Manager_StorageDto dto) {
		logger.info("storageModifyRegist.do 수정내용등록 : " + dto);
		
		service.registStorage(dto);
		
		return "redirect:storageDetail.do?storage_id="+dto.getStorage_id();
	}
	
	
}
