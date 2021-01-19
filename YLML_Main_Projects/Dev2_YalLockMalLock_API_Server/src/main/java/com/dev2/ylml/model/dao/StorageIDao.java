package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.StorageListDto;

public interface StorageIDao {

	/**
	 * 전체 보관함 정보 출력해서 마커표시
	 * @return List<Map<String, Object>>
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
	 * @return StorageListDto
	 */
	public StorageListDto ajaxCountStorage(String id);
	/**
	 * 해당 보관함의 사용여부 가져오기
	 * @param id
	 * @return List<StorageBoxDto>
	 */
	public List<StorageBoxDto> selectStorageStatus(String id);
	/**
	 * seq, id, email로 보관 등록
	 * @param map(boxSeq, id, email)
	 */
	public boolean insertGoods(Map<String, Object> map);
	/**
	 * 사용가능 -> 사용중 상태변경
	 * @param map(boxSeq, id)
	 */
	public boolean updateStatus(Map<String, Object> map);
	/**
	 * 결제 코드 등록
	 * @param map(boxSeq, id,cost_code)
	 */
	public boolean insertCost(Map<String, Object> map);
	/**
	 * 보관 정보에 selectKey로 받아온 결제 코드 수정
	 * @param map(boxSeq, id,cost_code)
	 */
	public boolean updateCostCode(Map<String, Object> map);
	/**
	 * 0시 기준 모든 보관함 사용중,사용대기->사용가능,사용불가로 변경
	 * @param id
	 * @return int
	 */
	public int updateAllStatus(List<String> list);
	/**
	 * 연장시간, 연장횟수 수정
	 * @param map(storageId, boxSeq)
	 */
	public boolean updateExtend(Map<String, Object> map);
	/**
	 * 연장 금액 수정
	 * @param map(storageId, boxSeq)
	 */
	public boolean updateExtendCost(Map<String, Object> map);
	/**
	 * 회수전 키 대조
	 * @param key
	 * @return cost_code
	 */
	public String compareKey(String key);
	/**
	 * 보관시간 만료 이후 회수시 키 대조후 결제 전에 할증비용 추가 
	 * @param costCode, overCost
	 */
	public boolean updateExtraCost(Map<String, Object> map);
	/**
	 * 결제완료 후 사용중인 보관함 대기여부 확인하고 사용가능 처리
	 * @param costCode + 쿼리에서 id 저장해야해서 map 사용
	 */
	public boolean updateStatusCheck(Map<String,String> map);
	/**
	 * 보관정보 삭제
	 * @param costCode
	 */
	public boolean deleteGoods(String costCode);
	/**
	 * 수령 사용자 아이디 확인하기
	 * @param email
	 * @return email
	 */
	public String checkOutEmail(String email);
	/**
	 * 입력한 수령 사용자 보관물품에 등록
	 * @param map(email,storageId,boxSeq)
	 */
	public boolean updateOutUser(Map<String,Object> map);
	/**
	 * 반품할때 결제코드로 정보 가져오기
	 * @param costCode
	 * @return StorageGoodsDto(BOX_SEQ ,STORAGE_ID , IN_USER ,OUT_USER)
	 */
	public StorageGoodsDto selectForReturn(String costCode);
	/**
	 * 반품 등록 
	 * @param StorageGoodsDto(BOX_SEQ ,STORAGE_ID , IN_USER ,OUT_USER,MESSAGE)
	 */
	public boolean insertReturn(StorageGoodsDto goodsDto);
}
