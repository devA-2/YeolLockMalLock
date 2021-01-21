package com.min.edu.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.RFIDDto;

@Service
public class RFIDService implements RFIDIService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RFIDIDao iDao;

	@Override
	public boolean insertGoods(RFIDDto dto) {
		log.info("------------------ 키 제외하고 물품 등록 ------------------");
		return iDao.insertGoods(dto);
	}

	@Override
	public boolean insertKey(RFIDDto dto) {
		log.info("------------------ 키 등록 ------------------");
		return iDao.insertKey(dto);
	}

	@Override
	public boolean updateOutUser(RFIDDto dto) {
		log.info("------------------ 키 전송 ------------------");
		return iDao.updateOutUser(dto);
	}

	@Override
	public boolean updateKey(RFIDDto dto) {
		log.info("------------------ STORAGE_GOODS의 KEY를 out_user의 키로 재설정해주기 ------------------");
		return iDao.updateKey(dto);
	}
	
}
