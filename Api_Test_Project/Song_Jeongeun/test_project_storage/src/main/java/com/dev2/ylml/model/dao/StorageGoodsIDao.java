package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxListDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.DeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;

public interface StorageGoodsIDao {
	
	/**
	 * 보관함ID로 STORAGEBOX_LIST 조회
	 */
	public StorageBoxListDto selectStorageBoxList(String storageId);
	
	/**
	 * 보관함SEQ, 보관함ID로 STORAGEBOX_GOODS 조회
	 */
	public StorageGoodsDto selectStorageGoods(Map<String, Object> map);
	
	/**
	 * 보관 정보 조회(사용자)
	 */
	public List<UserStorageListDto> selectUserStorageList(Map<String, String> map);
	
	/**
	 * 보관 비용 조회(사용자)
	 */
	public List<CostDto> selectCost(String email);
	
	/**
	 * 타임테이블 SEQ 조회
	 */
	public int selectTimeTableSeq(String subway);
	
	/**
	 * 역 갯수 조회
	 */
	public int selectSubwayCnt();
	
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
	 * 배송 등록
	 */
	public boolean insertDelivery(DeliveryDto dto);
	
	/**
	 * 보관 정보 업데이트
	 */
	public boolean updateDeliveryCode(StorageGoodsDto dto);
	
	/**
	 * 결제 금액 업데이트
	 */
	public boolean updateDeliveryCost(StorageGoodsDto dto);
	
	/**
	 * 배송 정보 조회(사용자)
	 */
	public List<DeliveryListDto> selectUserDeliveryList(String email);
	
	/**
	 * 배송 할당 물품 조회(배송원)
	 */
	public List<DeliveryListDto> selectDelmanDeliveryList(String email);

	/**
	 * 배송 시작 시간 업데이트
	 */
	public boolean updatedeliveryStrat(String deliveryCode);
	
	/**
	 * 결제 상태 업데이트
	 */
	public boolean updateCostStatus(String costCode);

// ========================= 지도 복붙 테스트 =========================
	/**
	 * 검색창 자동완성 위한 보관함 리스트 정보 받아오기
	 * @return List<StorageListDto>
	 */
	public List<Map<String,String>> selectStorageList();
	/**
	 * 전체 보관함 정보 출력해서 마커표시
	 * @return storage_id,lng,lat
	 */
	public List<Map<String, Object>> selectMap();
	
}