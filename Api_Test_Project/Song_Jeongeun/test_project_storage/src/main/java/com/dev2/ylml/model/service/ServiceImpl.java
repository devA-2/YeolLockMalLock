package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageBoxListDto;
import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.model.dao.StorageGoodsIDao;

@Service
public class ServiceImpl implements IService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StorageGoodsIDao sgDao;

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
		}
		logger.info("Service_selectUserStorageList 실행");
		return list;
	}
	
	@Override
	public int selectDeliveryQty(String storageId) {
		logger.info("Service_selectDeliveryQty 실행");
		return sgDao.selectDeliveryQty(storageId);
	}
	
	@Override
	public List<StorageBoxListDto> selectStorageBoxList(String storageId) {
		logger.info("Service_selectStorageBoxList 실행");
		return sgDao.selectStorageBoxList(storageId);
	}
	
	@Override
	public List<MemberDto> selectDeliveryMan() {
		logger.info("Service_selectDeliveryMan 실행");
		return sgDao.selectDeliveryMan();
	}

	@Override
	public int selectCurrnetLoc(Map<String, String> deliverManLoc) {
		String currnetLoc = sgDao.selectCurrnetLoc(deliverManLoc.get("deliverymanId"));
		int cnt = sgDao.selectDeliveryQty(currnetLoc);
		int time = 0;
		if(!currnetLoc.equals(deliverManLoc.get("userLoc"))) {
			if(cnt < 7) {
				Map<String, String> stations = new HashMap<String, String>();
				stations.put("startStation", currnetLoc);
				stations.put("arriveStation", deliverManLoc.get("userLoc"));
				time = sgDao.selectDeliveryTime(stations);
			}else {
				time = -1;
			}
		}
		logger.info("Service_selectCurrnetLoc 실행");
		return time;
	}

	@Override
	public Map<String, Integer> selectDeliveryInfo(Map<String, String> stations) {
		Map<String, Integer> info = new HashMap<String, Integer>();
		int time = sgDao.selectDeliveryTime(stations);
		int cost = sgDao.selectStationCost(stations);
		info.put("time", time);
		info.put("cost", cost);
		logger.info("Service_selectDeliveryInfo 실행");
		return info;
	}
	
//	@Override
//	public boolean insertDelivery(DeliveryDto delDto, StorageGoodsDto goodsDto) {
//		boolean isc1 = sgDao.insertDelivery(delDto);
//		boolean isc2 = sgDao.updateDeliveryCode(goodsDto);
//		logger.info("Service_insertDelivery 실행");
//		return (isc1 || isc2)? true:false;
//	}

	@Override
	public List<UserDeliveryListDto> selectUserDeliveryList(String email) {
		logger.info("Service_selectUserDeliveryList 실행");
		return sgDao.selectUserDeliveryList(email);
	}

}
