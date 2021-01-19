package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;

public interface StorageGoodsIDao {

	/**
	 * 보관 정보 조회(사용자)
	 */
	public List<UserStorageListDto> selectUserStorageList(String email);
	
	/**
	 * 보관 비용 조회(사용자)
	 */
	public List<CostDto> selectCost(String email);
	
	/**
	 * 전체 배송원 조회
	 */
	public List<String> selectDeliveryMan();
	
	/**
	 * 배송원 현재 위치 조회
	 */
	public String selectCurrnetLoc(String deliverymanId);
	
	/**
	 * 배송 물량 확인
	 */
	public int selectDeliveryQty(String storageId);
	
	/**
	 * 배송 소요 시간 계산
	 */
	public int selectDeliveryTime(Map<String, String> stations);
	
	/**
	 * 배송 비용 계산
	 */
	public int selectStationCost(Map<String, String> stations);

	/**
	 * 배송 정보 조회(사용자)
	 */
	public List<UserDeliveryListDto> selectUserDeliveryList(String email);
}
