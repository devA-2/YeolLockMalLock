package com.dev2.ylml.model.service;

import java.util.List;

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
	public int selectDeliveryGoods(String storageId) {
		return sgDao.selectDeliveryGoods(storageId);
	}

	@Override
	public List<UserDeliveryListDto> selectUserDeliveryList(String email) {
		return sgDao.selectUserDeliveryList(email);
	}

}
