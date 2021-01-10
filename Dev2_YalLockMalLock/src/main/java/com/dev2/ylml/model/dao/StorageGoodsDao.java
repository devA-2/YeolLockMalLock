package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;

@Repository
public class StorageGoodsDao implements StorageGoodsIDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<UserStorageListDto> selectUserStorageList(String email) {
		return sqlSession.selectList("storageBox.selectUserStorageList", email);
	}

	@Override
	public List<CostDto> selectCost(String email) {
		return sqlSession.selectList("storageBox.selectCost", email);
	}
	
	@Override
	public int selectDeliveryQty(String storageId) {
		return sqlSession.selectOne("storageBox.selectDeliveryQty", storageId);
	}
	
	@Override
	public List<String> selectDeliveryMan() {
		return sqlSession.selectList("storageBox.selectDeliveryMan");
	}
	
	@Override
	public String selectCurrnetLoc(String deliverymanId) {
		return sqlSession.selectOne("storageBox.selectCurrnetLoc", deliverymanId);
	}
	
	@Override
	public int selectDeliveryTime(Map<String, String> stations) {
		return sqlSession.selectOne("storageBox.selectDeliveryTime", stations);
	}

	@Override
	public int selectStationCost(Map<String, String> stations) {
		return sqlSession.selectOne("storageBox.selectStationCost", stations);
	}

	@Override
	public List<UserDeliveryListDto> selectUserDeliveryList(String email) {
		return sqlSession.selectList("storageBox.selectUserDeliveryList", email);
	}

}
