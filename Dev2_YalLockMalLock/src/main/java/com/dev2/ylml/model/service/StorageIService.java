package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;

public interface StorageIService {

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
}
