package com.dev2.ylml.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.CostDto;
import com.dev2.ylml.dto.UserDeliveryListDto;
import com.dev2.ylml.dto.UserStorageListDto;
import com.dev2.ylml.model.dao.StorageGoodsIDao;

@Service
public class ServiceImpl implements IService {
	
	@Autowired
	private StorageGoodsIDao sgDao;

	@Override
	public List<UserStorageListDto> selectUserStorageList(String email) {
		List<UserStorageListDto> list = sgDao.selectUserStorageList(email);
		List<CostDto> cost = sgDao.selectCost(email);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < cost.size(); j++) {
				if (list.get(i).getCostCode().equals(cost.get(j).getCostCode())) {
					list.get(i).setCost(cost.get(j).getCost());
				}
			}
		}
		return list;
	}
	
	@Override
	public int selectDeliveryQty(String storageId) {
		return sgDao.selectDeliveryQty(storageId);
	}
	
	@Override
	public List<String> selectDeliveryMan() {
		return sgDao.selectDeliveryMan();
	}

	@Override
	public int selectCurrnetLoc(Map<String, String> deliverManLoc) {
		String currnetLoc = sgDao.selectCurrnetLoc(deliverManLoc.get("deliverymanId"));
		int cnt = sgDao.selectDeliveryQty(currnetLoc);
		int time = 0;
		if(cnt < 7) {
			Map<String, String> locations = new HashMap<String, String>();
			locations.put("startStation", currnetLoc);
			locations.put("arriveStation", deliverManLoc.get("userLoc"));
			time = sgDao.selectDeliveryTime(locations);
			return time;
		}else {
			time = -1;
			return time;
		}
		
	}

	@Override
	public Map<String, Integer> selectDeliveryInfo(Map<String, String> stations) {
		Map<String, Integer> info = new HashMap<String, Integer>();
		int time = sgDao.selectDeliveryTime(stations);
		int cost = sgDao.selectStationCost(stations);
		info.put("time", time);
		info.put("cost", cost);
		return info;
	}

	@Override
	public List<UserDeliveryListDto> selectUserDeliveryList(String email) {
		return sgDao.selectUserDeliveryList(email);
	}


}
