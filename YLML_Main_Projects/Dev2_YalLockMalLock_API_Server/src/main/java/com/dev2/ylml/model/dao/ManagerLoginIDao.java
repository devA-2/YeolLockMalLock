package com.dev2.ylml.model.dao;

import java.util.Map;

import com.dev2.ylml.dto.Manager_MemberDto;

public interface ManagerLoginIDao {

	public Manager_MemberDto loginMember(Map<String, Object> map);
	
}
