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
		return sqlSession.selectList("storage.selectMap");
	}

	@Override
	public List<StorageListDto> selectStorageList() {
		return sqlSession.selectList("storage.selectStorageList");
	}

	@Override
	public StorageListDto ajaxCountStorage(String id) {
		return sqlSession.selectOne("storage.ajaxCountStorage",id);
	}

	@Override
	public List<StorageBoxDto> selectStorageStatus(String id) {
		return sqlSession.selectList("storage.selectStorageStatus",id);
	}

	@Override
	public boolean insertGoods(Map<String, Object> map) {
		return sqlSession.insert("storage.insertGoods",map)>0?true:false;
	}
	@Override
	public boolean updateStatus(Map<String, Object> map) {
		return sqlSession.update("storage.updateStatus",map)>0?true:false;
	}
	@Override
	public boolean insertCost(Map<String, Object> map) {
		return sqlSession.insert("storage.insertCost",map)>0?true:false;
	}

	@Override
	public boolean updateCostCode(Map<String, Object> map) {
		return sqlSession.update("storage.updateCostCode",map)>0?true:false;
	}

	@Override
	public int updateAllStatus(List<String> list) {
		return sqlSession.update("storage.updateAllStatus",list);
	}

	@Override
	public boolean updateExtend(Map<String, Object> map) {
		return sqlSession.update("storage.updateExtend",map)>0?true:false;
	}

	@Override
	public boolean updateExtendCost(Map<String, Object> map) {
		return sqlSession.update("storage.updateExtendCost",map)>0?true:false;
	}

	
	
	
	
}
