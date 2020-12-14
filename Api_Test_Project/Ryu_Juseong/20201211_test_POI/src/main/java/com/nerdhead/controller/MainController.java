package com.nerdhead.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nerdhead.dto.ExcelDto;

@Controller
public class MainController {
	@RequestMapping(value={"/downloadExcel.do"})
	public ModelAndView downloadExcel() throws Exception{
	    ModelAndView mav = new ModelAndView("excelView");
	    //테스트용 더미 데이터
	    String[] keys = {"테","스","트"};
	    List<HashMap<String, String>> data_list =
	    		new ArrayList<HashMap<String, String>>();
	    HashMap<String, String> value;
	    for (int i = 0; i < 5; i++) {
			value=new HashMap<>();
			for (int j = 0; j < keys.length; j++) {
				value.put(keys[j], Integer.toString(((i+1)*10+j+1)));
			}
		}
	    
	    ExcelDto excelDto=new ExcelDto();
	    excelDto.setVersion_XLSX();
	    excelDto.setFile_name("testExcel");
		excelDto.setKeys(keys);
		excelDto.setData_list(data_list);
	    
	    mav.addObject("excelDto", excelDto);
	    
	    return mav;
	}
}
