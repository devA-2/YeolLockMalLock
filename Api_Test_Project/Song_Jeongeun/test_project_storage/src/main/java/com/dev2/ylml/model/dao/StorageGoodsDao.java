package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxListDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.DeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;

@Repository
public class StorageGoodsDao implements StorageGoodsIDao {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public StorageBoxListDto selectStorageBoxList(String storageId) {
		logger.info("Dao_selectStorageBoxList 실행");
		return sqlSession.selectOne("storageBox.selectStorageBoxList", storageId);
	}
	
	@Override
	public StorageGoodsDto selectStorageGoods(Map<String, Object> map) {
		logger.info("Dao_selectStorageGoods 실행");
		return sqlSession.selectOne("storageBox.selectStorageGoods", map);
	}
	
	@Override
	public List<UserStorageListDto> selectUserStorageList(Map<String, String> map) {
		logger.info("Dao_selectUserStorageList 실행");
		return sqlSession.selectList("storageBox.selectUserStorageList", map);
	}

	@Override
	public List<CostDto> selectCost(String email) {
		logger.info("Dao_selectCost 실행");
		return sqlSession.selectList("storageBox.selectCost", email);
	}
	
	@Override
	public int selectTimeTableSeq(String subway) {
		logger.info("Dao_selectTimeTableSeq 실행");
		return sqlSession.selectOne("storageBox.selectTimeTableSeq", subway);
	}
	
	@Override
	public int selectSubwayCnt() {
		logger.info("Dao_selectSubwayCnt 실행");
		return sqlSession.selectOne("storageBox.selectSubwayCnt");
	}
	
	@Override
	public List<MemberDto> selectDeliveryMan() {
		logger.info("Dao_selectDeliveryMan 실행");
		return sqlSession.selectList("storageBox.selectDeliveryMan");
	}
	
	@Override
	public String selectDeliveryLoc(String deliverymanId) {
		logger.info("Dao_selectDeliveryLoc 실행");
		return sqlSession.selectOne("storageBox.selectDeliveryLoc", deliverymanId);
	}
	
	@Override
	public int selectDeliveryQty(String deliverymanId) {
		logger.info("Dao_selectDeliveryQty 실행");
		return sqlSession.selectOne("storageBox.selectDeliveryQty", deliverymanId);
	}
	
	@Override
	public int selectDeliveryTime(Map<String, Integer> subwaySeqs) {
		logger.info("Dao_selectDeliveryTime 실행");
		return sqlSession.selectOne("storageBox.selectDeliveryTime", subwaySeqs);
	}
	
	@Override
	public boolean insertDelivery(DeliveryDto dto) {
		int cnt = sqlSession.insert("storageBox.insertDelivery", dto);
		logger.info("Dao_insertDelivery 실행");
		return cnt > 0? true:false;
	}
	
	@Override
	public boolean updateDeliveryCode(StorageGoodsDto dto) {
		int cnt = sqlSession.update("storageBox.updateDeliveryCode", dto);
		logger.info("Dao_updateDeliveryCode 실행");
		return cnt > 0? true:false;
	}
	
	@Override
	public boolean updateDeliveryCost(StorageGoodsDto dto) {
		int cnt = sqlSession.insert("storageBox.updateDeliveryCost", dto);
		logger.info("Dao_updateDeliveryCost 실행");
		return cnt > 0? true:false;
	}

	@Override
	public List<DeliveryListDto> selectUserDeliveryList(String email) {
		logger.info("Dao_selectUserDeliveryList 실행");
		return sqlSession.selectList("storageBox.selectUserDeliveryList", email);
	}

	@Override
	public List<DeliveryListDto> selectDelmanDeliveryList(String email) {
		logger.info("Dao_selectDelmanDeliveryList 실행");
		return sqlSession.selectList("storageBox.selectDelmanDeliveryList", email);
	}

	@Override
	public boolean updatedeliveryStrat(String deliveryCode) {
		int cnt = sqlSession.update("storageBox.updatedeliveryStrat", deliveryCode);
		logger.info("Dao_updatedeliveryStrat 실행");
		return cnt > 0? true:false;
	}

	
// ========================= 지도 복붙 테스트 =========================	

	@Override
	public List<Map<String, Object>> selectMap() {
		return sqlSession.selectList("storageBox.selectMap");
	}

}
