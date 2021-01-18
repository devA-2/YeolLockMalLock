package com.dev2.ylml.model;

import java.util.Map;

import com.dev2.ylml.dto.Manager_MemberDto;

public interface ManagerLoginIService {

	
	/** 관리자 로그인 
	 * @param map
	 * @return
	 */
	public Manager_MemberDto loginMember(Map<String, Object> map);
}
