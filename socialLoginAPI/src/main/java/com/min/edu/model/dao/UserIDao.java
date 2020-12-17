package com.min.edu.model.dao;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.UserDto;


public interface UserIDao {
	
	/**
	 * memberList : 전체회원 조회
	 */
	public List<UserDto> memList();
	
	/**
	 * signupMember : 회원가입
	 */
	public boolean signupMember(UserDto dto);
	public boolean signupNaver(UserDto dto);
	
	/**
	 * idDuplicateCheck : 아이디 중복 체크
	 */
	public boolean emailCheck(String email);
	
	/**
	 * loginMember : 로그인
	 */
	public UserDto loginMember(Map<String, Object> map);
	
}
