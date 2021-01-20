/**
 * 
 */
package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;

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
	
	
	
	
	
	
	
	/**
	 * 전체 보관함 정보 출력해서 마커표시
	 * @return storage_id,lng,lat
	 */
	public List<Map<String, Object>> selectMap();
	/**
	 * 검색창 자동완성 위한 보관함 리스트 정보 받아오기
	 * @return List<StorageListDto>
	 */
	public List<Map<String,String>> selectStorageList();
	/**
	 * ajax로 해당 보관함 정보, 사용가능한 갯수 가져오기
	 * @param id
	 * @return id,name,subway,address,detail,cnt
	 */
	public StorageListDto ajaxCountStorage(String id);
	/**
	 * 해당 보관함의 사용여부 가져오기
	 * @param id
	 * @return box_seq , box_status
	 */
	public List<StorageBoxDto> selectStorageStatus(String id);
	/**
	 * 보관함seq, id, 사용자email 받아서 보관 등록
	 * 보관 정보 등록 + 해당보관함 사용중처리 + 결제코드 등록 + 결제코드 수정
	 * @param boxSeq, id, email
	 */
	public boolean insertGoods(Map<String, Object> map);
	/**
	 * 0시 기준 모든 보관함 사용중,사용대기->사용가능,사용불가로 변경
	 * @param list
	 * @return int
	 */
	public int updateAllStatus(List<String> list);
	/**
	 * 연장시간, 연장횟수 수정 + 연장 금액 수정
	 * @param map
	 */
	public boolean updateExtend(Map<String, Object> map);
	/**
	 * 회수전 키 대조
	 * @param key
	 * @return cost_code
	 */
	public String compareKey(String key);
	/**
	 * 키 일치할때 결제 전에 할증비용 추가 
	 * @param costCode, overCost
	 */
	public boolean updateExtraCost(Map<String, Object> map);
	/**
	 * 결제완료 후 
	 * 사용중인 보관함 대기여부 확인하고 사용가능 처리 + 물품 정보 삭제 
	 * @param costCode + selectKey id 저장해야해서 map 사용
	 */
	public boolean afterPayment(Map<String,String> map);
	/**
	 * 수령 사용자 아이디 확인하기
	 * @param email
	 * @return email
	 */
	public String checkOutEmail(String email);
	/**
	 * 입력한 수령 사용자 보관물품에 등록
	 * @param map
	 */
	public boolean updateOutUser(Map<String,Object> map);
	/**
	 * 결제 완료후 반품 신청
	 * 물품 정보 받아옴 + 물품정보 삭제 + 반품 등록 + 결제코드 등록 + 결제코드 수정
	 * @param map(costCode,message)
	 */
	public boolean insertReturn(Map<String,String> map);
}
