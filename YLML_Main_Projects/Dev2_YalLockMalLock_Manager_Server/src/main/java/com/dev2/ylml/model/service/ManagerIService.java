/**
 * 
 */
package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.Map;

import com.dev2.ylml.dto.MemberDto;

/**
 * @author nerdhead
 *
 */
public interface ManagerIService {
	/**
	 * Sample 참고용 method!!!
	 * @param id
	 * @param pw
	 * @return
	 */
	HashMap<String, String> getSampleData(String id, String pw);
	
	/**
	 * 로그인
	 * 
	 * @param map
	 * @return MemberDto
	 */
	public MemberDto login(Map<String, Object> map);

}
