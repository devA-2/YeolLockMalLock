package com.dev2.ylml.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Api Web Server<br>
 * jackson을 이용한 json <-> dto 컨버팅 기능 제공<br>
 * 
 * @author nerdhead
 *
 */
//TODO : 일반 서버로 부터 POST로 받은 @RequestBody 값을 dto에 담아주는 method
// list형태로 무조건 오게 되며 0번째는 항상 key가 온다

// checkKey(data)?getData(data):null;

//TODO : dto를 json객체로 바꿔주는 기능

public class ApiServerHelper {
	private final String KEY;
	private final boolean KEY_UNCHECK;

	/**
	 * param key_uncheck가 없으면 기본값인 false가 적용된다
	 * 
	 * @param key
	 */
	
	/*
	 * public ApiServerHelper(String key) { this.KEY = key; this.KEY_UNCHECK =
	 * false; }
	 */

	public ApiServerHelper(String key, Boolean key_uncheck) {
		this.KEY = key;
		this.KEY_UNCHECK = key_uncheck;
	}
	public Map<String, Object> generateData(){
		Class<Void> clazz = Void.TYPE;
		return generateData(clazz);
		
	}
	
	public Map<String, Object> generateData(Object data) {
		Map<String, Object> map= keySucceed();
		//팀원들에게 Null을 쓰지말라고 전파하였으나 null을 유용하게 사용하고있어서 null 값도 처리하게 (억지로) 수정함..
		if(data!=null) {
			map.put("data",data);
			Type[] types = data.getClass().getGenericInterfaces();
		}else {
			map.put("data","null");
			map.put("className", "null");
		}
		
		
		return map;
	}

	/**
	 * null이거나 list의 0번째 값이 null이거나, key값이 없거나 key값이 일치 하지 않을경우 false<br>
	 * 이외의 경우에는 true를 return 한다.<br>
	 * 단 KEY_UNCHECK가 true면 조건을 무시하고 true를 return 한다
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkKey(Map<String, Object> map) {
		return KEY_UNCHECK
				|| (map != null
						&& (map.containsKey("key"))
						&& ( map.get("key").equals(KEY)));
	}

	private Map<String, Object> generateArray(boolean certification) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Certification", certification);
		

		return map;
	};

	private Map<String, Object> keySucceed() {
		return generateArray(true);
	};

	public Map<String, Object> keyFailed() {
		return generateArray(false);
	}
	// TODO : 예외 처리 해야함
	public Object getData(Map<String, Object> map) {
		ObjectMapper mapper = new ObjectMapper();
		Class<?> clazz;
		Object data=null;
		try {
			clazz = Class.forName((String)map.get("className"));
			String dataStr=(String) map.get("data");
			data = mapper.readValue(dataStr, clazz);
			System.out.println(data);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		

		return data;
	};
}
