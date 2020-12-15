package com.nerdhead.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nerdhead.abstractView.ExcelDownload;
import com.nerdhead.dto.ExcelDto;


@Controller
public class MainController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ExcelDownload excelView;
	
	@RequestMapping(value="/downloadExcel.do")
	public ModelAndView downloadExcel() throws Exception{
		if(excelView==null) {
			logger.info("downloadExcel -> excelView is null!!!");
		}
	    ModelAndView mav = new ModelAndView(excelView);
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
			data_list.add(value);
		}
	    
	    ExcelDto excelDto=new ExcelDto();
	    excelDto.setVersion_XLSX();
	    excelDto.setFile_name("testExcel");
		excelDto.setKeys(keys);
		excelDto.setData_list(data_list);
	    
	    mav.addObject("excelDto", excelDto);
	    return mav;
	}
	
	@RequestMapping(value = "test.do")
	public String test() {return "test";}
}
