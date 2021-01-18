package com.dev2.ylml.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev2.ylml.model.service.IApiService;


@Controller
public class ApiController {
	private final int KEY =1234;
	
	
	@Autowired
	IApiService service;
	
	@ResponseBody
	@RequestMapping(value = "index.do")
	public List<HashMap<String, Object>> index(){
		return service.testList();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "service.do")
	public List<Map<String, Object>> service(@RequestBody Map<String, String> data){
		System.out.println("================================================");
	    System.out.println(data);
	    System.out.println("================================================");
		
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Cert", true);
		list.add(map);

		map=new HashMap<String, Object>();
		map.put("id", "wntjd337@gmail.com");
		map.put("pw", "1234");
		list.add(map);
		
		
		return list;
	}
	
}
