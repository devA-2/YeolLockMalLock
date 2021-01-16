package com.dev2.ylml.model;

import java.util.Map;

import com.dev2.ylml.dto.Manager_MemberDto;

public interface ManagerLoginIService {

	
	public Manager_MemberDto loginMember(Map<String, Object> map);
}
