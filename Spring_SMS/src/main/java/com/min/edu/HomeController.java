package com.min.edu;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.edu.model.API_Service;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private API_Service service;
	
	@RequestMapping(value = "hello",method = RequestMethod.GET)
	public String move(Model model) {
		logger.info("test {} :",new Date());
		return "test";
	}
	
	@RequestMapping(value = "/sendSMS.do",method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(String phoneNumber) {

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        service.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
    }
	
	
	
}
