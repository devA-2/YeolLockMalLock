package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.dto.UserGoodsDto;

public interface StorageIService {


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
	 * @return cost_code, out_user
	 */
	public UserGoodsDto compareKey(String key);
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
	 * @param costCode
	 * @param message
	 */
	public boolean insertReturn(String costCode,String message);
	
}
