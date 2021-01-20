package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.util.ApiClientHelper;


@Service
public class UserService implements UserIService{
	@Autowired
	ApiClientHelper helper;

	@SuppressWarnings("unchecked")
	public HashMap<String, String> getSampleData(String id, String pw) {
		//URL+PW로 데이터를 만들어서 전달
		//list의 0번째인 Certification check후 false일경우 return null;
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		return (HashMap<String, String>)helper.request("sampleData.do", map);
	}
	
	/*
	 * 회원가입
	 */
	public boolean insertMember(MemberDto memberDto) {
		return (boolean)helper.request("insertMember.do", memberDto);
	}
	
	/*
	 * 아이디 중복체크
	 */
	public int idCheck(String email) {
		return (int)helper.request("idCheck.do", email);
	}
	
	/*
	 * 휴대폰 중복체크
	 */
	public int phoneCheck(String phoneNum) {
		return (int)helper.request("phoneCheck.do", phoneNum);
		
	}
	
	/*
	 * 로그인
	 */
	public MemberDto login(Map<String, Object> map) {
		return (MemberDto)helper.request("login.do", map);
	}
	
	/*
	 * api 간편 로그인
	 */
	public MemberDto apiLogin(Map<String, Object> map) {
		return (MemberDto)helper.request("naverCallback.do", map);
	}
	
	/*
	 * 일반회원 임시권한 변경
	 */
	public boolean authUpdate(MemberDto dto) {
		return (boolean)helper.request("checkCode.do", dto);
	}
	
	/*
	 * 아이디 찾기
	 */
	public String idSearch(Map<String, Object> map) {
		return (String)helper.request("idSearch,do", map);
	}
	
	/*
	 * 비밀번호 찾기
	 */
	public int pwSearch(Map<String, String> map) {
		return (int)helper.request("pwSearch.do", map);
	}
	
	/*
	 * 개인정보 변경
	 */
	public int updateInfo(Map<String, Object> map) {
		return (int)helper.request("updateInfo.do", map);
	}
	
	/*
	 * 비밀번호 변경
	 */
	public int updatePw(MemberDto dto) {
		return (int)helper.request("updatePw.do", dto);
	}
	
	/*
	 * 탈퇴전 사용서비스 확인
	 */
	public int usingCheck(String email) {
		return (int)helper.request("usingCheck.do", email);
	}
	
	/*
	 * 회원탈퇴
	 */
	public int quitMember(String email) {
		return (int)helper.request("quitMember.do", email);
	}
}
