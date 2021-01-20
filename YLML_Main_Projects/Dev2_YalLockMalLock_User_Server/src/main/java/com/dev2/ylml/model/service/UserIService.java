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
public interface UserIService {
	/**
	 * Sample 참고용 method!!!
	 * @param id
	 * @param pw
	 * @return
	 */
	HashMap<String, String> getSampleData(String id, String pw);
	
	/**
	 * 회원가입 
	 * @param memberDto
	 * @return boolean
	 */
	public boolean insertMember(MemberDto memberDto);
	
	/**
	 * 아이디 중복체크
	 * @param email
	 * @return
	 */
	public int idCheck(String email);
	
	/**
	 * 휴대폰 중복체크
	 * @param phoneNum
	 * @return int
	 */
	public int phoneCheck(String phoneNum);
	
	/**
	 * 로그인
	 * 
	 * @param map
	 * @return MemberDto
	 */
	public MemberDto login(Map<String, Object> map);
	
	/**
	 * api 간편 로그인
	 * 
	 * @param map
	 * @return MemberDto
	 */
	public MemberDto apiLogin(Map<String, Object> map);
	
	/**
	 * 일반회원 임시권한 변경
	 * 
	 * @param dto
	 * @return boolean
	 */
	public boolean authUpdate(MemberDto dto);
	
	/**
	 * 아이디 찾기
	 * 
	 * @param map
	 * @return String(
	 */
	public String idSearch(Map<String, Object> map);
	
	/**
	 * 비밀번호 찾기
	 * 
	 * @param map
	 * @return int
	 */
	public int pwSearch(Map<String, String> map);
	
	/**
	 * 개인정보 변경(지금은 휴대폰 번호만 변경가능하지만, 추후에 추가 가능)
	 * 
	 * @param map
	 * @return int
	 */
	public int updateInfo(Map<String, Object> map);
	
	/**
	 * 비밃번호 변경
	 * 
	 * @param dto
	 * @return int
	 */
	public int updatePw(MemberDto dto);
	
	/**
	 * 탈퇴전 서비스 사용여부 확인
	 * 
	 * @param email
	 * @return int
	 */
	public int usingCheck(String email);
	
	/**
	 * 회원탈퇴
	 * 
	 * @param email
	 * @return int
	 */
	public int quitMember(String email);
	
}
