package com.dev2.ylml.model.service;

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
	public int selectTimeTableSeq(String subway) {
		logger.info("Service_selectTimeTableSeq 실행");
		return sgDao.selectTimeTableSeq(subway);
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
		boolean isc1 = sgDao.updateDeliveryCode(goodsDto);
		String deliveryCode = sgDao.selectDeliveryCode(goodsDto);
		System.out.println("업데이트된 배송코드!! "+deliveryCode);
		delDto.setDeliveryCode(deliveryCode);
		System.out.println("DeliveryDto 확인!! "+delDto);
		boolean isc2 = sgDao.insertDelivery(delDto);
		// TODO : 결제 업데이트에서 오류 발생! 확인하기!!
		boolean isc3 = sgDao.updateDeliveryCost(goodsDto);
		logger.info("Service_insertDelivery 실행");
		return (isc1 || isc2 || isc3)? true:false;
	}

	@Override
	public List<UserDeliveryListDto> selectUserDeliveryList(String email) {
		logger.info("Service_selectUserDeliveryList 실행");
		return sgDao.selectUserDeliveryList(email);
	}

}
