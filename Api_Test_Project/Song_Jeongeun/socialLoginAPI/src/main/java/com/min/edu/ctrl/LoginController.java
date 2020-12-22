package com.min.edu.ctrl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
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
public class LoginController {
	
	private NaverLoginBO naverLoginBO;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	
	@Autowired
	private UserIService service;
	
	@RequestMapping(value = "/loginForm.do", method = RequestMethod.GET)
    public String loginForm(Model model, HttpSession session) {
		String naverUrl = naverLoginBO.getAuthorizationUrl(session);
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String googleUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		
		model.addAttribute("naverUrl", naverUrl);
		model.addAttribute("googleUrl", googleUrl);
        return "loginForm";
    }
 
	@RequestMapping(value = "/naverCallback.do", method = RequestMethod.GET)
	public String naverCallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
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
	
	@RequestMapping(value = "/googleCallback.do", method = RequestMethod.GET)
	public String googleCallback(HttpServletRequest request, Model model) throws IOException, Exception {
		String code = request.getParameter("code");
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(), null);
		String accessToken = accessGrant.getAccessToken();
		String reqURL = "https://www.googleapis.com/userinfo/v2/me?access_token="+accessToken; 

		try { 
			URL url = new URL(reqURL); 
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			int responseCode = conn.getResponseCode(); 
			if(responseCode == 200){ 
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = ""; 
				String result = ""; 
				
				while ((line = br.readLine()) != null) {
					result += line;
				}
				
				JSONParser parser = new JSONParser();
				System.out.println("result : "+result);
				JSONObject obj = (JSONObject) parser.parse(result);

//				String name = obj.get("name").toString();
				String email = obj.get("email").toString();
				
				boolean emailChk = service.emailCheck(email);
				
				if(emailChk != true) {
//					model.addAttribute("name",name);
					model.addAttribute("email",email);
					return "extraForm";
				}else {
					return "callback";
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/extraInfo.do", method = RequestMethod.POST)
	public String signUp(UserDto dto) {
		boolean isc = service.signupNaver(dto);
		return isc? "redirect:/loginForm.do":"redirect:/index.do";
	}
	
    
}
