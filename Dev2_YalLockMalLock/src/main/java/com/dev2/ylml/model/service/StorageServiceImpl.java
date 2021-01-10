package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;
import com.dev2.ylml.model.dao.StorageIDao;

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
	
	
}
