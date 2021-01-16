package com.dev2.ylml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping(value = "index.do")
	public String index() {
		
		
		return "";
	}
}
