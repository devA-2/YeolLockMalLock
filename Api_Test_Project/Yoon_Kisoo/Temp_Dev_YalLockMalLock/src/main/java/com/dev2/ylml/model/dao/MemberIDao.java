package com.dev2.ylml.model.dao;

import java.util.Map;

import com.dev2.ylml.dto.MemberDto;

public interface MemberIDao {
	
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
	 *  회원가입
	 */
	public MemberDto login(Map<String, Object> map);
	
	/*
	 * 이메일 인증 후, 권한변경
	 */
	public boolean authUpdate(MemberDto dto);
	/*
	 *  아이디 찾기
	 */
	public String idSearch(Map<String, Object> map);
	
	/*
	 *  개인정보 변경(지금은 휴대폰 번호만 변경 가능하지만, 추후에 추가 할 수 있음)
	 */
	public int updateInfo(Map<String, Object> map);
	
	/*
	 * 비밀번호 변경
	 */
//	public int updatePw(Map<String, Object> map);
	
	public int updatePw(MemberDto dto);
	
	/*
	 * 탈퇴전 서비스 사용유무 조회
	 */
	public int usingCheck(String email);
	
	/*
	 * 회원탈퇴 (삭제여부 변경)
	 */
	public int quitMember(String email);
}
