package com.dev2.ylml.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiClientHelper {
	private final String URL;
	private final String KEY;
	
	
	public ApiClientHelper(String url, String key) {
		this.URL=url;
		this.KEY=key;
		
		System.out.println("ApiClientHelper 빈 생성 -> URL : "+URL+", KEY : "+KEY);
	}
	public Object request(String path) {
		Class<Void> clazz = Void.TYPE;
		return request(path, clazz);
	}
	
	public <T> Object request(String path, T data) {
		JSONObject result = requestData(path, data);
		boolean cert =(boolean)result.get("Certification");
		if(!cert||result.get("data")==null||result.get("className")==null) {
			System.out.println("ApiClientHelper : request failed\n "
					+ "   ㄴ result -> "+result);
			return null;
 		}
		return getData(result);
	}
	
	
	//TODO : dto같은거 jackson사용해서 dto.class 인자로 받아서 convert to String 구현
	public <T> T request(String path, Class<?> data, Class<T> clazz) {
		JSONObject result = requestData(path, data);
		boolean cert =(boolean)result.get("Certification");
		//JSONArray의 첫번쨰 Certification의 true/false 확인
		if(!cert||result.get("data")==null) {
			System.out.println("ApiClientHelper : request failed(result -> "+result+")");
			return null;
 		}
		String responseData=(String)result.get("data");
		
		return getData(responseData, clazz);
	}
	private Object getData(JSONObject result) {
		String responseData=result.get("data").toString();
		try {
			Class<?> clazz=Class.forName((String) result.get("className"));
			return getData(responseData, clazz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private <T> T getData(String responseData, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(responseData, clazz);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	private <T> JSONObject requestData(String path, T data) {
		//defaultArray 생성
		JSONObject json = getDefaultArray();
		ObjectMapper mapper = new ObjectMapper();
		String dataStr;
		HttpPost request = new HttpPost(URL+path);
	    StringEntity params;
	    JSONObject result = null;
	    
		try {
			dataStr = mapper.writeValueAsString(data);
			
			
			json.put("data", dataStr);
			json.put("className", data.getClass().getName());
			System.out.println("ApiClientHelper requestData -> JSON : "+json.toJSONString());
			System.out.println("ApiClientHelper requestData -> DATA : "+data);
			
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			
		  //data -> String
			params = new StringEntity(json.toString(), "UTF-8");
			//TODO : 추후 header 추가 따로 설정으로 뺄것..
			request.addHeader("content-type", "application/json; charset=UTF-8");
		    request.setEntity(params);
		    //POST 전송(URL+path, StringData)
		    CloseableHttpResponse response = httpClient.execute(request);
		    System.out.println("================================================");
		    String result_str=EntityUtils.toString(response.getEntity());
		    System.out.println(result_str);
		  //결과값을 JSONObject에 저장
		    result=(JSONObject)(new JSONParser().parse(result_str));
		    System.out.println("================================================");
		    response.close();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	
	
	
	@SuppressWarnings("unchecked")
	private JSONObject getDefaultArray() {
		JSONObject json = new JSONObject();
		json.put("key", KEY);
		
		return json;
	}
	
}
