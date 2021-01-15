package com.dev2.ylml.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.Manager_StorageDto;

@Service
public class Manager_StorageServiceImpl implements Manager_StorageIService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Manager_StorageIDao service;
	
	// 보관함 전체 조회
	@Override
	public List<Manager_StorageDto> selectAllStorage() {
		logger.info("보관함 전체 조회 selectAllStorage {}");
		return service.selectAllStorage();
	}
	
	// 보관함 ID로 조회
	@Override
	public Manager_StorageDto selectIdStorage(String storage_id) {
		logger.info("보관함 id 조회 selectIdStorage {}");
		return service.selectIdStorage(storage_id);
	}
	
	// 보관함 지하철역으로 조회
	@Override
	public Manager_StorageDto selectSubwayStorage(String subway) {
		logger.info("보관함 지하철역 조회 selectSubwayStorage {}");
		return service.selectSubwayStorage(subway);
	}
	
	// 보관함 상세정보 조회
	@Override
	public Manager_StorageDto selectDetailStorage(String storage_id) {
		logger.info("보관함 상세정보 조회 selectDetailStorage {}");
		return service.selectDetailStorage(storage_id);
	}

	// 보관함 등록
	@Override
	public boolean registStorage(Manager_StorageDto dto) {
		logger.info("보관함 등록 registStorage {}");
		return service.registStorage(dto);
	}

	// 보관함 수정
	@Override
	public boolean modifyStorage(Manager_StorageDto dto) {
		logger.info("보관함 수정 modifyStorage {}");
		return service.modifyStorage(dto);
	}
	
	// 사용불가 보관함이 해결되었을때 관리자가 사용 가능 보관함으로 변경
	@Override
	public boolean ActivateStorage(int seq) {
		logger.info("보관함 사용가능 변경 ActivateStorage {}");
		return service.ActivateStorage(seq);
	}

	

	

}
