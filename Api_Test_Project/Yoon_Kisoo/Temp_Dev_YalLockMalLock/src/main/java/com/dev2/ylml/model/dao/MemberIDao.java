package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.UserGoodsDto;

public interface MemberIDao {
	
	/**
	 * 회원가입
	 * @param dto
	 * @return boolean
	 */
	public boolean insertMember(MemberDto dto);
	
	/**
	 * 아이디 중복검사
	 * @param email
	 * @return int
	 */
	public int idCheck(String email);
	
	/**
	 * 휴대폰 중복검사
	 * @param phoneNum
	 * @return
	 */
	public int phoneCheck(String phoneNum);
	
	/**
	 * 로그인
	 * @param map
	 * @return dto
	 */
	public MemberDto login(Map<String, Object> map);
	
	/**
	 * api 간편 로그인
	 * @param map
	 * @return dto
	 */
	public MemberDto apiLogin(Map<String, Object> map);
	
	/**
	 * 일반회원 임시권한 변경
	 * @param dto
	 * @return boolean
	 */
	public boolean authUpdate(MemberDto dto);
	
	/**
	 * 아이디 찾기 
	 * @param map
	 * @return String
	 */
	public String idSearch(Map<String, Object> map);
	
	/**
	 * 비밀번호 찾기 (입력한 정보 유효한지 검사)
	 * @param map
	 * @return int
	 */
	public int pwSearch(Map<String, String> map);
	
	/**
	 * 개인정보 변경(지금은 휴대폰 번호만 변경 가능하지만, 추후에 추가 될 수 있음)
	 * @param map
	 * @return
	 */
	public int updateInfo(Map<String, Object> map);
	
	/**
	 * 비밀번호 변경
	 * @param dto
	 * @return int
	 */
	public int updatePw(MemberDto dto);
	
	/**
	 * 탈퇴전 서비스 사용여부확인
	 * @param email
	 * @return int
	 */
	public int usingCheck(String email);
	
	/**
	 * 회원탈퇴
	 * @param email
	 * @return int
	 */
	public int quitMember(String email);
	
	/**
	 * 회원 전체 목록 조회
	 * @param email
	 * @return list
	 */
	public List<MemberDto> selectAll(String email);
	
	/**
	 * 회원 아이디로 검색
	 * @return list
	 */
	public List<String> memberIdSearch();
	
	/**
	 * 회원 상세 정보 조회
	 * @param email
	 * @return dto
	 */
	public MemberDto detailMember(String email);
	
	/**
	 * 회원 사용중인 서비스 조회
	 * @param email
	 * @return list
	 */
	public List<UserGoodsDto> memberUsing(String email);

}
