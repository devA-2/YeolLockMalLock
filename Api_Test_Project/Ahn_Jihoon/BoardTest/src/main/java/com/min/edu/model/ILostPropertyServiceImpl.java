package com.min.edu.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.LostPropertyDto;

@Service
public class ILostPropertyServiceImpl implements ILostPropertyService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ILostPropertyDao iDao;
	
	@Override
	public List<LostPropertyDto> selectAllLostProperty() {
		log.info("-------------------------- 유실물 전체 조회 --------------------------");
		return iDao.selectAllLostProperty();
	}

	@Override
	public LostPropertyDto selectOneLostProperty(String seq) {
		log.info("-------------------------- 유실물 상세 조회 --------------------------");
		return iDao.selectOneLostProperty(seq);
	}

}