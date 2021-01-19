package com.dev2.ylml.model.dao;

import java.util.Map;

import com.dev2.ylml.dto.MemberDto;

public interface MemberIDao {
	
	public boolean insertMember(MemberDto dto);
	
	public int idCheck(String email);
	
	public int phoneCheck(String phone_num);
	
	public MemberDto login(Map<String, Object> map);
	
	

}
