package com.dev2.ylml.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.model.service.ApiService;


@Controller
public class ApiController {
	private final int KEY =1234;
	
	
	@Autowired
	ApiService service;
	
	@ResponseBody
	@RequestMapping(value = "index.do")
	public List<HashMap<String, Object>> index(){
		
		
		return service.testList();
	}
	
}
