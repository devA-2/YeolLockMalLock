package com.dev2.ylml.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ApiService implements IApiService {
	private List<HashMap<String, Object>> generateArray(boolean certification){
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> certMap = new HashMap<String, Object>();
		certMap.put("Certification", certification);
		
		
		list.add(certMap);
		
		return list;
	};

	private List<HashMap<String, Object>> keySucceed(){
		return generateArray(true);
	};

	private List<HashMap<String, Object>> keyFailed(){
		return generateArray(false);
	};
	
	
	public List<HashMap<String, Object>> testList(){
		return keySucceed();
	}
}
