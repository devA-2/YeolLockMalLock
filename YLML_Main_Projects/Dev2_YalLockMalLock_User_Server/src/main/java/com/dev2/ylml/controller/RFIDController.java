package com.dev2.ylml.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dev2.ylml.dto.RFIDDto;
import com.dev2.ylml.model.service.UserIService;

@Controller
public class RFIDController {

	@Autowired
	UserIService iService;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/RFID.do", method = RequestMethod.GET)
	public String RFIDDoing() {
		
		return "RFID";
	}
	
	
	
	@RequestMapping(value = "/insertKey.do", method = RequestMethod.POST)
	public String insertKey(RFIDDto dto) {
		boolean isc = iService.insertKey(dto);
		if (isc==true) {
			return "redirect:/RFID.do";
		}else {
			return "error";			
		}
		
	}
	
	
	@RequestMapping(value = "/updateKey.do", method=RequestMethod.POST)
	public String updateKey(RFIDDto dto) {
		boolean isc = iService.updateKey(dto);
		if (isc == true) {
			return "redirect:/RFID.do";
		}else {
			return "error";
		}
	}
	
}
