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
	
	/*
	 *  아이디 찾기
	 */
	public MemberDto IdSearch(Map<String, Object> map);
	
	/*
	 *  개인정보 변경(지금은 휴대폰 번호만 변경 가능하지만, 추후에 추가 할 수 있음
	 */
	public boolean updateInfo(Map<String, Object> map);

}
