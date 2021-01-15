package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;

public interface IApiService {
	/*
	 * certification 값을 받아서 유효 여부에따라
	 * true -> keySucceed() -> generateArray(true)로 배열을 받아서 추가 hashmap들 받아서 return
	 * false -> keyFailed() -> return generateArray(false)
	 * 
	 *  private 요소는 interface에 구현 할 필요가 없음
	 */
	
	
	//List<HashMap<String, Object>> generateArray(boolean certification);
	//List<HashMap<String, Object>> keySucceed();
	//List<HashMap<String, Object>> keyFailed();
}
