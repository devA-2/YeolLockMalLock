/**
 * 
 */
package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;

/**
 * @author nerdhead
 *
 */
public interface DeliveryIService {
	/**
	 * Sample 참고용 method!!!
	 * @param id
	 * @param pw
	 * @return
	 */
	HashMap<String, String> getSampleData(String id, String pw);
	String getSampleData2();
	
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
	public MemberDto enDelLogin(Map<String, Object> map);
	
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
	 * 회원탈퇴
	 * 
	 * @param email
	 * @return int
	 */
	public int quitMember(String email);
	
	
	
	/**
	 * 배송 정보 조회(사용자, 배송원)
	 */
	public List<DeliveryDto> selectDeliveryList(String email, String auth);
	
	/**
	 * 배송 시작 시간 업데이트
	 */
	public boolean updatedeliveryStrat(String deliveryCode);
	
}

