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
	public String storageList() {
		logger.info("storageList.do : 보관함 전체 조회 이동");

		return "storageList";
	}
	
	// 보관함 전체 조회  storageList
	// 전체조회에서 id로 담당자 및 배송원 조회 검색 결과를 list로 model객체에 담아서
	// storageViewList로 전달
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
		return "storageViewList";
	}
	
	
	//  보관함 상세조회 : ID로 조회 storageDetail.do
	//	+보관함 상태조회 : ID로 조회
	@RequestMapping(value = "storageDetail.do", method = RequestMethod.GET)
	public String storageDetail(Model model,@RequestParam String storage_id) {
		logger.info("storageDetail.do storage_id 검색id : " + storage_id);
		
		List<Manager_StorageDto> lists = service.selectBoxStatus(storage_id);
		Manager_StorageDto list = service.selectDetailStorage(storage_id);
		
		logger.info("storageDetail.do box_status : " + lists);
		
		model.addAttribute("lists", lists);
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
	
	//	사용불가 보관함이 해결되었을때 관리자가 사용 가능 보관함으로 변경
	// ActivateStorage.do
	@RequestMapping(value = "ActivateStorage.do", method = RequestMethod.GET)
	public String ActivateStorage(Model model, @RequestParam String box_seq, 
			@RequestParam String storage_id) {
		logger.info("ActivateStorage.do 상태변경할 보관함번호 : " + box_seq);
		logger.info("ActivateStorage.do 상태변경되는 보관함 id  : " + storage_id);
		
		service.ActivateStorage(box_seq);
		
		return "redirect:storageDetail.do?storage_id="+storage_id;
	}
	
	// 지하철역으로 검색 viewSubway.do
	
	
	
	
}
