package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxListDto;
import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;

public interface IService {

	/**
	 * 보관 정보 조회(사용자)
	 */
	public List<UserStorageListDto> selectUserStorageList(Map<String, String> map);
	
	/**
	 * 배송 물량 확인
	 */
	public int selectDeliveryQty(String storageId);
	
	/**
	 * 보관함ID로 보관함 정보 조회
	 */
	public List<StorageBoxListDto> selectStorageBoxList(String storageId);

	/**
	 * 배송원 정보 조회
	 */
	public List<MemberDto> selectDeliveryMan();
	public int selectCurrnetLoc(Map<String, String> deliverManLoc);
	
	/**
	 * 배송 소요 시간 및 비용 계산
	 */
	public Map<String, Integer> selectDeliveryInfo(Map<String, String> stations);
	
	/**
	 * 배송 등록
	 */
//	public boolean insertDelivery(DeliveryDto delDto, StorageGoodsDto goodsDto);
	
	/**
	 * 배송 정보 조회(사용자)
	 */
	public List<UserDeliveryListDto> selectUserDeliveryList(String email);
	
}
