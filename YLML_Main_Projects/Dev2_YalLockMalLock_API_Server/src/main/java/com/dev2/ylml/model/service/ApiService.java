package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.util.ApiServerHelper;
@Service
public class ApiService {
	@Autowired
	ApiServerHelper helper;
	
	//Certification -> 데이터의 0번째는 key 값 -> 틀리면 Certification:false로 return  한다
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSampleData(Map<String, Object> map){
		//무조건 if(checkKey) return문을 추가해줘야함
		if(!helper.checkKey(map)) {
			return helper.keyFailed();
		}
		Map<String, String> data = (Map<String, String>) helper.getData(map);
		System.out.println(data.get("id")+" / "+ map.get("pw"));
		
		
		//dao에서 받아온 데이터는 helper.generateData()에 담아서 전달함
		return helper.generateData(data);
	}
	
	
}
