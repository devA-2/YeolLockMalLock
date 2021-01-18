package com.dev2.ylml.controller;


import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.model.service.TestService;

@Controller
public class TestController {
	@Autowired
	TestService service;

	@RequestMapping(value = "index.do")
	public String index() {
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value = "service.do")
	public JSONArray service() {
		return service.getAccount();
	}
}
