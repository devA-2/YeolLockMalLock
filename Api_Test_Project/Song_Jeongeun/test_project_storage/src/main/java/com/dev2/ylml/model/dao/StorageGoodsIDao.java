package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxListDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;

public interface StorageGoodsIDao {

	/**
	 * 보관 정보 조회(사용자)
	 */
	public List<UserStorageListDto> selectUserStorageList(Map<String, String> map);
	
	/**
	 * 보관 비용 조회(사용자)
	 */
	public List<CostDto> selectCost(String email);
	
	/**
	 * 보관함ID로 보관함 정보 조회
	 */
	public List<StorageBoxListDto> selectStorageBoxList(String storageId);
	
	/**
	 * 타임테이블 SEQ 조회
	 */
	public int selectTimeTableSeq(String subway);
	
	/**
	 * 전체 배송원 조회
	 */
	public List<MemberDto> selectDeliveryMan();
	
	/**
	 * 배송원 현재 위치 조회
	 */
	public String selectDeliveryLoc(String deliverymanId);
	
	/**
	 * 배송 물량 확인
	 */
	public int selectDeliveryQty(String deliverymanId);
	
	/**
	 * 배송 소요 시간 계산
	 */
	public int selectDeliveryTime(Map<String, Integer> subwaySeqs);
	
	/**
	 * 보관 정보 업데이트
	 */
	public boolean updateDeliveryCode(StorageGoodsDto dto);
	
	/**
	 * 배송 코드 조회
	 */
	public String selectDeliveryCode(StorageGoodsDto dto);
	
	/**
	 * 배송 등록
	 */
	public boolean insertDelivery(DeliveryDto dto);
	
	/**
	 * 결제 금액 업데이트
	 */
	public boolean updateDeliveryCost(StorageGoodsDto dto);
	
	/**
	 * 배송 정보 조회(사용자)
	 */
	public List<UserDeliveryListDto> selectUserDeliveryList(String email);
}
