package com.min.edu.ctrl;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.edu.model.KakaoService;
import com.min.edu.vo.KakaoVo;

@Controller
public class KakaoController {
	
	@Autowired
	private KakaoService iService;
	
	@RequestMapping(value = "/kakao.do", method = RequestMethod.GET)
	public String selectAll(Model model) {
		KakaoVo list = iService.selectAll();
		System.out.println(list);
		
		model.addAttribute("list",list);
		
		return "kakao";
	}
	
	
	
	
}
