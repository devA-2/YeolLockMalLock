package com.dev2.ylml.model.service;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	/*
	 * 1. jackson mapper로 dto 형식 상관없이 컨버팅 -> service에서 helper 갖다가 쓰게 구현할것			--> jacksonHelper.class
	 * 2. NameSpace URL, key를 jacksonHelper에 contructor Args로 전달해서 구현?
	 * 3. 해당 jacksonHelper를 @Autowired 사용하기
	 * 4. 
	 * */
	
	
	private final String NS ="http://localhost:8091/Sample_Dev2_YLML_API_Server/";
	private final String KEY ="1234";
	
	
	public JSONArray getAccount() {
		return requestService("service.do");
	}
	
	private JSONArray requestService(String url_do) {
		JSONObject json = new JSONObject();
		json.put("key", KEY);
		
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(NS+url_do);
	    StringEntity params;
	    JSONArray result = null;
		try {
			params = new StringEntity(json.toString());
			request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    CloseableHttpResponse response = httpClient.execute(request);
		    System.out.println("================================================");
		    String result_str=EntityUtils.toString(response.getEntity());
		    System.out.println(result_str);
		    result=(JSONArray)(new JSONParser().parse(result_str));
		    System.out.println("================================================");
		    response.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}
