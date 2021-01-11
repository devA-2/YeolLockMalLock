package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;

public interface StorageIDao {

	/*
	 * 전체 보관함 지도 출력
	 */
	public List<Map<String, Object>> selectMap();
	/*
	 * 자동완성 위한 리스트 받아오기
	 */
	public List<StorageListDto> selectStorageList();
	/*
	 * ajax로 현재 보관함 정보, 사용가능 갯수 받아오기
	 */
	public StorageListDto ajaxCountStorage(String id);
	/*
	 * id로 개별보관함 상태정보 가져오기
	 */
	public List<StorageBoxDto> selectStorageStatus(String id);
	/*
	 * 보관함번호, id, 보관email 받아서 보관 등록 
	 */
	public boolean insertGoods(Map<String, Object> map);
	/*
	 * 해당 보관함 사용가능->사용중 처리
	 */
	public boolean updateStatus(Map<String, Object> map);
	/*
	 * 결제코드 등록
	 */
	public boolean insertCost(Map<String, Object> map);
	/*
	 * 셀렉트 키로 받아온 결제코드 업데이트
	 */
	public boolean updateCostCode(Map<String, Object> map);
	
	
}
