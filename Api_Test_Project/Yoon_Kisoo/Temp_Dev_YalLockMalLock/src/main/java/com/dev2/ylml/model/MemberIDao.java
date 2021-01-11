package com.dev2.ylml.model;

import java.util.Map;

import com.dev2.ylml.dto.MemberDto;
import com.mysql.fabric.xmlrpc.base.Member;

public interface MemberIDao {
	
	public boolean insertMember(MemberDto dto);
	
	public int idCheck(String email);
	
	public int phoneCheck(String phone_num);
	
	public MemberDto login(Map<String, Object> map);
	
	public MemberDto IdSearch(Map<String, Object> map);
	

}
