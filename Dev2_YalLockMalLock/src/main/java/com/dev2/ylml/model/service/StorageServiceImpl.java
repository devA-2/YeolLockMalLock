package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.dto.UserGoodsDto;
import com.dev2.ylml.model.dao.StorageIDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StorageServiceImpl implements StorageIService {
	
	@Autowired
	private StorageIDao dao;

	
	@Override
	public List<Map<String, Object>> selectMap() {
		return dao.selectMap();
	}

	@Override
	public List<Map<String,String>> selectStorageList() {
		return dao.selectStorageList();
	}

	@Override
	public StorageListDto ajaxCountStorage(String id) {
		return dao.ajaxCountStorage(id);
	}

	@Override
	public List<StorageBoxDto> selectStorageStatus(String id) {
		return dao.selectStorageStatus(id);
	}

	@Transactional
	@Override
	public boolean insertGoods(Map<String, Object> map) {
		boolean isc1 = dao.insertGoods(map);
		log.info("보관 등록 : "+isc1+" - map :"+ map);
		boolean isc2 = dao.updateStatus(map);
		log.info("보관함 사용중 처리 : "+isc2+" - map :"+ map);
		boolean isc3 = dao.insertCost(map);
		log.info("결제코드 생성 : "+isc3+" - map :"+ map);
		boolean isc4 = dao.updateCostCode(map);
		log.info("결제코드 수정 : "+isc4+" - map :"+ map);
		return isc1 && isc2 && isc3 && isc4;
	}

	@Override
	public int updateAllStatus(List<String> list) {
		return dao.updateAllStatus(list);
	}

	@Override
	public boolean updateExtend(Map<String, Object> map) {
		boolean isc1 = dao.updateExtend(map);
		log.info("연장 시간, 횟수 수정 결과 : " +isc1);
		boolean isc2 = dao.updateExtendCost(map);
		log.info("연장 금액 수정 결과 : " +isc2);
		return isc1 && isc2;
	}

	@Override
	public UserGoodsDto compareKey(String key) {
		return dao.compareKey(key);
	}

	@Override
	public boolean updateExtraCost(Map<String, Object> map) {
		return dao.updateExtraCost(map);
	}
	
	@Transactional
	@Override
	public boolean afterPayment(Map<String, String> map) {
		boolean isc1 = dao.updateStatusCheck(map);
		log.info("결제코드로 해당 보관함 사용가능/사용불가 처리후 결과 : "+isc1);
		boolean isc2 = dao.deleteGoods(map.get("costCode"));
		log.info("결제 완료된 물품 정보 삭제 결과 : "+isc2);
		return isc1 && isc2;
	}

	@Override
	public String checkOutEmail(String email) {
		return dao.checkOutEmail(email);
	}

	@Override
	public boolean updateOutUser(Map<String, Object> map) {
		return dao.updateOutUser(map);
	}
	@Transactional
	@Override
	public boolean insertReturn(String costCode,String message) {
		UserGoodsDto goodsDto = dao.selectForReturn(costCode);
		boolean isc1 = dao.deleteGoods(costCode);
		log.info("결제 완료된 물품 정보 삭제 결과 : "+ isc1);
		goodsDto.setMessage(message);
		log.info("반품 하기 위해 받아온 Dto : "+ goodsDto);
		boolean isc2 = dao.insertReturn(goodsDto);
		log.info("반품 등록 결과 : "+isc2);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", goodsDto.getStorageId());
		map.put("boxSeq", goodsDto.getBoxSeq());
		boolean isc3 = dao.insertCost(map);
		log.info("반품 결제코드 생성 : "+isc3);
		boolean isc4 = dao.updateCostCode(map);
		log.info("반품 결제코드 수정 : "+isc4);
		return isc1 && isc2 && isc3 && isc4;
	}


}
