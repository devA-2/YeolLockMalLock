package com.min.edu.model;

import java.util.Map;

import com.min.edu.dto.MemberDto;

public interface TestILoginDao {

	public MemberDto loginMember(Map<String, Object> map);
	
	
}
