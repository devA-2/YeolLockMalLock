package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;

public interface StorageIService {

	/**
	 * 전체 보관함 정보 출력해서 마커표시
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> selectMap();
	/**
	 * 검색창 자동완성 위한 보관함 리스트 정보 받아오기
	 * @return List<StorageListDto>
	 */
	public List<StorageListDto> selectStorageList();
	/**
	 * ajax로 해당 보관함 정보, 사용가능한 갯수 가져오기
	 * @param id
	 * @return 
	 */
	public StorageListDto ajaxCountStorage(String id);
	/**
	 * 해당 보관함의 사용여부 가져오기
	 * @param id
	 * @return 
	 */
	public List<StorageBoxDto> selectStorageStatus(String id);
	/**
	 * 보관함seq, id, 사용자email 받아서 보관 등록(해당보관함 사용중처리 + 결제코드 등록 + 결제코드 수정)
	 * @param boxSeq, id, emaim
	 * @return boolean
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
	 * @return boolean
	 */
	public boolean updateExtend(Map<String, Object> map);
}
