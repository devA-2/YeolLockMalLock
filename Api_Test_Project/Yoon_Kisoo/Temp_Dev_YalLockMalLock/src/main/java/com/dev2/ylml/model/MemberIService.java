package com.dev2.ylml.model;

import java.util.Map;

import com.dev2.ylml.dto.MemberDto;

public interface MemberIService {
	
	/*
	 *  회원가입(일반회원)
	 */
	public boolean insertMember(MemberDto dto);
	
	/*
	 *  아이디 중복검사(Ajax)
	 */
	public int idCheck(String email);
	
	/*
	 *  휴대폰 중복검사(Ajax)
	 */
	public int phoneCheck(String phone_num);
	
	/*
	 *  회원가입(일반회원)
	 */
	public MemberDto login(Map<String, Object> map);

}
