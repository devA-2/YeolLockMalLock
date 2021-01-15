package com.dev2.ylml.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.DeliveryDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxListDto;
import com.dev2.ylml.dto.StorageGoodsDto;
import com.dev2.ylml.dto.DeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.model.dao.StorageGoodsIDao;

@Service
public class ServiceImpl implements IService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StorageGoodsIDao sgDao;

	@Override
	public StorageBoxListDto selectStorageBoxList(String storageId) {
		logger.info("Service_selectStorageBoxList 실행");
		return sgDao.selectStorageBoxList(storageId);
	}
	
	@Override
	public StorageGoodsDto selectStorageGoods(Map<String, Object> map) {
		logger.info("Service_selectStorageGoods 실행");
		return sgDao.selectStorageGoods(map);
	}
	
	@Override
	public List<UserStorageListDto> selectUserStorageList(Map<String, String> map) {
		List<UserStorageListDto> list = sgDao.selectUserStorageList(map);
		List<CostDto> cost = sgDao.selectCost(map.get("email"));
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < cost.size(); j++) {
				if (list.get(i).getCostCode().equals(cost.get(j).getCostCode())) {
					list.get(i).setCost(cost.get(j).getCost());
				}
			}
			int overTime = list.get(i).getOverTime();
			int overH = overTime/60;
			int overM= overTime%overH;
			int overCost = overH * 1000;
			list.get(i).setOverH(overH);
			list.get(i).setOverM(overM);
			list.get(i).setOverCost(overCost);
		}
		
		logger.info("Service_selectUserStorageList 실행");
		return list;
	}

	@Override
	public int selectTimeTableSeq(String subway) {
		logger.info("Service_selectTimeTableSeq 실행");
		return sgDao.selectTimeTableSeq(subway);
	}
	
	@Override
	public int selectSubwayCnt() {
		logger.info("Service_selectSubwayCnt 실행");
		return sgDao.selectSubwayCnt();
	}
	
	@Override
	public List<MemberDto> selectDeliveryMan() {
		logger.info("Service_selectDeliveryMan 실행");
		return sgDao.selectDeliveryMan();
	}
	
	@Override
	public String selectDeliveryLoc(String deliverymanId) {
		logger.info("ServiceselectDeliveryLoc 실행");
		return sgDao.selectDeliveryLoc(deliverymanId);
	}
	
	@Override
	public int selectDeliveryQty(String deliverymanId) {
		logger.info("Service_selectDeliveryQty 실행");
		return sgDao.selectDeliveryQty(deliverymanId);
	}

	@Override
	public int selectDeliveryTime(Map<String, Integer> subwaySeqs) {
		logger.info("Service_selectDeliveryTime 실행");
		return sgDao.selectDeliveryTime(subwaySeqs);
	}
	
	@Override
	public boolean insertDelivery(DeliveryDto delDto, StorageGoodsDto goodsDto) {
		boolean isc1 = sgDao.insertDelivery(delDto);
		goodsDto.setDeliveryCode(delDto.getDeliveryCode());
		if(goodsDto.getCategoryCode().equals("R")) {
			goodsDto.setCategoryCode("RD");
		}else {
			goodsDto.setCategoryCode("D");
		}
		boolean isc2 = sgDao.updateDeliveryCode(goodsDto);
		boolean isc3 = sgDao.updateDeliveryCost(goodsDto);
		logger.info("Service_insertDelivery 실행") ;
		return (isc1 || isc2 || isc3)? true:false;
	}

	@Override
	public List<DeliveryListDto> selectDeliveryList(String email, String auth) {
		List<DeliveryListDto> deliveryList = new ArrayList<DeliveryListDto>();
		StorageBoxListDto SBDto = new StorageBoxListDto();
		if(auth.equals("10")) {
			deliveryList = sgDao.selectUserDeliveryList(email);
		}else if(auth.equals("80")) {
			deliveryList = sgDao.selectDelmanDeliveryList(email);
			for (int i = 0; i < deliveryList.size(); i++) {
				String station = deliveryList.get(i).getOutboxId();
				SBDto = sgDao.selectStorageBoxList(station);
				station = SBDto.getSubway();
				deliveryList.get(i).setOutboxId(station);
			}
		}
		logger.info("Service_selectUserDeliveryList 실행");
		return deliveryList;
	}

	@Override
	public boolean updatedeliveryStrat(String deliveryCode) {
		logger.info("Service_updatedeliveryStrat 실행");
		return sgDao.updatedeliveryStrat(deliveryCode);
	}
	
	@Override
	public boolean updateCostStatus(String costCode) {
		logger.info("Service_updateCostStatus 실행");
		return sgDao.updateCostStatus(costCode);
	}
	
// ========================= 지도 복붙 테스트 =========================	
	@Override
	public List<Map<String,String>> selectStorageList() {
		return sgDao.selectStorageList();
	}
	@Override
	public List<Map<String, Object>> selectMap() {
		return sgDao.selectMap();
	}

}