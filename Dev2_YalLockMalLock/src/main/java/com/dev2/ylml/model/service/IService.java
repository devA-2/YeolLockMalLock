package com.dev2.ylml.model.service;

import java.util.List;

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
	
	/**
	 * 배송 정보 조회(사용자)
	 */
	public List<UserDeliveryListDto> selectUserDeliveryList(String email);
	
}
