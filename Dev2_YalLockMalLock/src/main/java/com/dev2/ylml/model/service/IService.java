package com.dev2.ylml.model.service;

import java.util.List;
<<<<<<< HEAD
import java.util.Map;

import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;

public interface IService {

	/**
	 * 보관 정보 조회(사용자)
	 */
	public List<UserStorageListDto> selectUserStorageList(String email);
	
	/**
	 * 배송 물량 확인
	 */
	public int selectDeliveryQty(String storageId);

	/**
	 * 배송원 정보 조회
	 */
	public List<String> selectDeliveryMan();
	public int selectCurrnetLoc(Map<String, String> deliverManLoc);
	
	/**
	 * 배송 소요 시간 및 비용 계산
	 */
	public Map<String, Integer> selectDeliveryInfo(Map<String, String> stations);
=======

import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;

public interface IService {

	/**
	 * 보관 정보 조회(사용자)
	 */
	public List<UserStorageListDto> selectUserStorageList(String email);
	
	/**
	 * 배송 물량 확인
	 */
	public int selectDeliveryGoods(String storageId);
>>>>>>> branch 'SONGJEONGEUN' of https://github.com/devA-2/YeolLockMalLock.git
	
	/**
	 * 배송 정보 조회(사용자)
	 */
	public List<UserDeliveryListDto> selectUserDeliveryList(String email);
	
}
