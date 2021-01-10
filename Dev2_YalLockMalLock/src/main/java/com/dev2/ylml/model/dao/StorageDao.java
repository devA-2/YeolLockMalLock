package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageListDto;

@Repository
public class StorageDao implements StorageIDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Map<String, Object>> selectMap() {
		return sqlSession.selectList("storages.selectMap");
	}

	@Override
	public List<StorageListDto> selectStorageList() {
		return sqlSession.selectList("storages.selectStorageList");
	}

	@Override
	public StorageListDto ajaxCountStorage(String id) {
		return sqlSession.selectOne("storages.ajaxCountStorage",id);
	}

	@Override
	public List<StorageBoxDto> selectStorageStatus(String id) {
		return sqlSession.selectList("storages.selectStorageStatus",id);
	}
	
	
}
