package com.dev2.ylml.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dev2.ylml.dto.Manager_StorageDto;
import com.dev2.ylml.model.Manager_StorageIService;

@Controller
public class Manager_StorageController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Manager_StorageIService service;
	
	// 보관함 전체 조회 StorageList.do
	@RequestMapping(value = "StorageList.do", method = RequestMethod.GET)
	public String StorageList(Model model) {
		logger.info("StorageList.do : 보관함 전체 조회 이동");

		List<Manager_StorageDto> lists = service.selectAllStorage();
		model.addAttribute("lists", lists);
		return "StorageList";
	}
	
	// storagelist
	
	
}
