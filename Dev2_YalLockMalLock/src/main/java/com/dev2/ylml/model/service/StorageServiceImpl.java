package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;
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
	public List<StorageListDto> selectStorageList() {
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
	
	
}
