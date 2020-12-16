package com.min.edu.ctrl;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.min.edu.dto.UserDto;
import com.min.edu.model.service.UserIService;
import com.min.edu.naver.NaverLoginBO;


@Controller
public class NaverLoginController {
	
	private NaverLoginBO naverLoginBO;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@Autowired
	private UserIService service;
	
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
    public String loginForm(Model model, HttpSession session) {
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		model.addAttribute("url", naverAuthUrl);
        return "loginForm";
    }
 
	@RequestMapping(value = "/callback.do", method = RequestMethod.GET)
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		String apiResult = naverLoginBO.getUserProfile(oauthToken);
		
		// String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject)obj;

		// 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		// response의 nickname값 파싱
		String name = (String)response_obj.get("name");
		String email = (String)response_obj.get("email");
		
		boolean emailChk = service.emailCheck(email);
		
		if(emailChk != true) {
			model.addAttribute("name",name);
			model.addAttribute("email",email);
			return "extraForm";
		}else {
			return "callback";
		}
	}
	
	@RequestMapping(value = "/extraInfo.do", method = RequestMethod.POST)
	public String signUp(UserDto dto) {
		boolean isc = service.signupNaver(dto);
		return isc? "redirect:/loginForm.do":"redirect:/index.do";
	}
	
    
}
