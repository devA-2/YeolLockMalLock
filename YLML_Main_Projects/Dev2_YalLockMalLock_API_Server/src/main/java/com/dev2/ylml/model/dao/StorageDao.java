package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxDto;
import com.dev2.ylml.dto.StorageGoodsDto;
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
	public List<Map<String,String>> selectStorageList() {
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
	public int tagNFC(MemberDto mem) {
		return sqlSession.selectOne("storage.tagNFC",mem);
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
	public int updateAllStatus(List<StorageGoodsDto> list) {
		int sum=0;
		for (int i = 0; i < list.size(); i++) {
			sum += sqlSession.update("storage.updateAllStatus",list.get(i).getStorageId());
		}
		return sum;
	}
	@Override
	public int deleteAllGoods() {
		return sqlSession.delete("storage.deleteAllGoods");
	}
	@Override
	public boolean updateExtend(Map<String, Object> map) {
		return sqlSession.update("storage.updateExtend",map)>0?true:false;
	}

	@Override
	public boolean updateExtendCost(Map<String, Object> map) {
		return sqlSession.update("storage.updateExtendCost",map)>0?true:false;
	}

	@Override
	public CostDto compareKey(String key) {
		return sqlSession.selectOne("storage.compareKey",key);
	}

	@Override
	public boolean updateExtraCost(Map<String, Object> map) {
		return sqlSession.update("storage.updateExtraCost",map)>0?true:false;
	}

	@Override
	public boolean updateStatusCheck(Map<String, String> map) {
		return sqlSession.update("storage.updateStatusCheck",map)>0?true:false;
	}
	
	@Override
	public boolean deleteGoods(String costCode) {
		return sqlSession.delete("storage.deleteGoods",costCode)>0?true:false;
	}
	@Override
	public String checkOutEmail(String email) {
		return sqlSession.selectOne("storage.checkOutEmail",email);
	}
	@Override
	public boolean updateOutUser(Map<String, Object> map) {
		return sqlSession.update("storage.updateOutUser",map)>0?true:false;
	}
	@Override
	public StorageGoodsDto selectForReturn(String costCode) {
		return sqlSession.selectOne("storage.selectForReturn",costCode);
	}
	@Override
	public boolean insertReturn(StorageGoodsDto goodsDto) {
		return sqlSession.insert("storage.insertReturn",goodsDto)>0?true:false;
	}





		
	
	
}
