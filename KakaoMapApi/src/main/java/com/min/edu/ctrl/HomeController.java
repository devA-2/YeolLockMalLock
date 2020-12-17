package com.min.edu.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.edu.dto.LockerDTO;
import com.min.edu.model.LockerIService;

@Controller
public class HomeController {

	@Autowired
	private LockerIService service;
	
	@RequestMapping(value = "/nowLocation.do")
	public String nowLocation() {
		System.out.println("nowLocation.do");
		return "nowLocation";
	}
	
	@RequestMapping(value = "/SearchLocker.do")
	public String SearchKeyword(Model model) {
		System.out.println("SearchLocker.do");
		List<LockerDTO> list = service.selectAll();
		
		StringBuffer markers =new StringBuffer();
		markers.append("[");
		for(LockerDTO dto : list) {
//			{title: '가산디지털단지', latlng: new kakao.maps.LatLng(37.480671149590194,126.88349684457698)},
			markers.append("{title:'"+dto.getTitle()+"', latlng: new kakao.maps.LatLng("+dto.getLat()+","+dto.getLng()+")},");
		}
		markers.deleteCharAt(markers.lastIndexOf(","));
		markers.append("]");
		
		model.addAttribute("markers",markers.toString());
		return "SearchLocker";
	}

}
